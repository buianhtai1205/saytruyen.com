package vn.com.saytruyen.user_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.exception.BusinessException;
import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.EncodeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.JwtUtil;
import io.github.buianhtai1205.saytruyen_common_service.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.saytruyen.user_service.constant.RoleEnum;
import vn.com.saytruyen.user_service.constant.UserServiceConst;
import vn.com.saytruyen.user_service.constant.UserServiceMessage;
import vn.com.saytruyen.user_service.converter.UserConverter;
import vn.com.saytruyen.user_service.model.RefreshToken;
import vn.com.saytruyen.user_service.model.User;
import vn.com.saytruyen.user_service.model.UserRole;
import vn.com.saytruyen.user_service.repository.RefreshTokenRepository;
import vn.com.saytruyen.user_service.repository.RoleRepository;
import vn.com.saytruyen.user_service.repository.UserRepository;
import vn.com.saytruyen.user_service.repository.UserRoleRepository;
import vn.com.saytruyen.user_service.request.LoginRequest;
import vn.com.saytruyen.user_service.request.UserSignUpRequest;
import vn.com.saytruyen.user_service.response.LoginResponse;
import vn.com.saytruyen.user_service.service.AuthService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Auth service.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final MessageSource messageSource;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Instantiates a new Auth service.
     *
     * @param userRepository         the user repository
     * @param messageSource          the message source
     * @param roleRepository         the role repository
     * @param userRoleRepository     the user role repository
     * @param refreshTokenRepository the refresh token repository
     * @param redisTemplate          the redis template
     */
    @Autowired
    public AuthServiceImpl(
            UserRepository userRepository,
            MessageSource messageSource,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            RefreshTokenRepository refreshTokenRepository, RedisTemplate<String, Object> redisTemplate) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        Optional<User> user = userRepository.findUserAndRoleToLogin(request.getUsername());

        // validate check user
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(
                    messageSource.getMessage(
                            UserServiceMessage.UT_E000001,
                            null,
                            LocaleContextHolder.getLocale()
                    )
            );
        }

        // validate check password
        if (!EncodeUtils.matches(request.getPassword(), user.get().getPassword())) {
            throw new BusinessException(
                    messageSource.getMessage(
                            UserServiceMessage.UT_E000002,
                            null,
                            LocaleContextHolder.getLocale()
                    )
            );
        }

        List<String> roles = user.get().getUserRoles().stream()
                .map(item -> item.getRole().getRoleName())
                .collect(Collectors.toList());
        String token = JwtUtil.generateToken(request.getUsername(), roles);

        refreshTokenRepository.deleteAllByUser(user.get());

        // create refresh token
        String refreshToken = RandomUtils.generateRandomString(12);
        // save refresh token
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .token(refreshToken)
                        .expiryDate(Instant.now().plusSeconds(86400 * 30)) // 30 days
                        .user(user.get())
                        .build()
        );

        return LoginResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional
    public void signUp(UserSignUpRequest request) {
        // save user info
        User user = UserConverter.INSTANCE.userSignUpRequestToUser(request);
        user.setPassword(EncodeUtils.encode(request.getPassword()));
        user.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        user.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        user.setDeleted(Boolean.FALSE);
        User userSignUp = userRepository.saveAndFlush(user);

        // save user role
        userRoleRepository.saveAndFlush(
                UserRole.builder()
                        .user(userSignUp)
                        .role(roleRepository.findByRoleName(RoleEnum.USER.getValue()))
                        .build());
    }

    @Override
    @Transactional
    public void logout(String token) {
        String username = JwtUtil.extractUsername(token);

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException(
                    messageSource.getMessage(
                            UserServiceMessage.UT_E000001,
                            null,
                            LocaleContextHolder.getLocale()
                    )
            );
        }

        if (!JwtUtil.validateToken(token, user.getUsername())) {
            throw new BusinessException(
                    messageSource.getMessage(
                            UserServiceMessage.UT_E000003,
                            null,
                            LocaleContextHolder.getLocale()
                    )
            );
        }

        long expiredDate = JwtUtil.extractExpiration(token).getTime();

        long systemDate = System.currentTimeMillis();

        // Time for expired token
        long timeToExpired = (expiredDate - systemDate) / 1000;

        if (timeToExpired > 0) {
            this.addTokenToBlackList(token, timeToExpired);
        }

        refreshTokenRepository.deleteAllByUser(user);
    }

    @Override
    public void addTokenToBlackList(String token, long timeToExpire) {
        // save token to redis
        redisTemplate.opsForValue().set(token, UserServiceConst.BLACKLISTED, Duration.ofSeconds(timeToExpire));
    }

    @Override
    public boolean isTokenInBlackList(String token) {
        return redisTemplate.hasKey(token);
    }

    @Override
    public Object userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)
                && Objects.nonNull(authentication.getCredentials())
                && !isTokenInBlackList(authentication.getCredentials().toString())) {
            return authentication.getPrincipal();
        } else throw new BadCredentialsException(
                messageSource.getMessage(
                        UserServiceMessage.UT_E000005,
                        null,
                        LocaleContextHolder.getLocale()
                )
        );
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByToken(refreshToken);
        if (refreshTokenOptional.isPresent()) {
            RefreshToken refreshTokenEntity = refreshTokenOptional.get();
            if (Objects.isNull(refreshTokenEntity.getUser())) {
                throw new BusinessException(
                        messageSource.getMessage(
                                UserServiceMessage.UT_E000004,
                                null,
                                LocaleContextHolder.getLocale()
                        )
                );
            }
            if (refreshTokenEntity.getExpiryDate().compareTo(Instant.now()) < 0) {
                refreshTokenRepository.delete(refreshTokenEntity);
                throw new BusinessException(
                        messageSource.getMessage(
                                UserServiceMessage.UT_E000005,
                                null,
                                LocaleContextHolder.getLocale()
                        )
                );
            }
            User user = refreshTokenEntity.getUser();
            List<String> roles = user.getUserRoles().stream()
                    .map(item -> item.getRole().getRoleName())
                    .collect(Collectors.toList());
            String token = JwtUtil.generateToken(user.getUsername(), roles);
            return LoginResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new ResourceNotFoundException(
                    messageSource.getMessage(
                            UserServiceMessage.UT_E000005,
                            null,
                            LocaleContextHolder.getLocale()
                    )
            );
        }
    }
}
