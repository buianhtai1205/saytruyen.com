package vn.com.saytruyen.user_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.exception.BusinessException;
import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.EncodeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.user_service.constant.RoleEnum;
import vn.com.saytruyen.user_service.constant.UserServiceMessage;
import vn.com.saytruyen.user_service.converter.UserConverter;
import vn.com.saytruyen.user_service.model.User;
import vn.com.saytruyen.user_service.model.UserRole;
import vn.com.saytruyen.user_service.repository.RoleRepository;
import vn.com.saytruyen.user_service.repository.UserRepository;
import vn.com.saytruyen.user_service.request.LoginRequest;
import vn.com.saytruyen.user_service.request.UserSignUpRequest;
import vn.com.saytruyen.user_service.response.LoginResponse;
import vn.com.saytruyen.user_service.service.AuthService;

import java.util.List;
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

    /**
     * Instantiates a new Auth service.
     *
     * @param userRepository the user repository
     * @param messageSource  the message source
     * @param roleRepository the role repository
     */
    @Autowired
    public AuthServiceImpl(
            UserRepository userRepository,
            MessageSource messageSource,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.roleRepository = roleRepository;
    }

    @Override
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

        return LoginResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public void signUp(UserSignUpRequest request) {
        User user = UserConverter.INSTANCE.userSignUpRequestToUser(request);
        user.setPassword(EncodeUtils.encode(request.getPassword()));
        user.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        user.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        user.setDeleted(Boolean.FALSE);
        user.setUserRoles(List.of(
                UserRole.builder()
                        .role(roleRepository.findByRoleName(RoleEnum.USER.getValue()))
                        .build()));
        userRepository.save(user);
    }
}
