package vn.com.saytruyen.user_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.constant.CommonConstants;
import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.saytruyen.user_service.constant.UserServiceConst;
import vn.com.saytruyen.user_service.converter.UserConverter;
import vn.com.saytruyen.user_service.model.User;
import vn.com.saytruyen.user_service.repository.UserRepository;
import vn.com.saytruyen.user_service.request.UserRequest;
import vn.com.saytruyen.user_service.response.UserResponse;
import vn.com.saytruyen.user_service.service.UserService;

import java.util.Objects;
import java.util.Optional;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PageableResponse getListUser(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? CommonConstants.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? CommonConstants.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<User> lstUser = userRepository.findAll(pageable);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lstUser.getTotalPages())
                .totalElements(lstUser.getTotalElements())
                .data(UserConverter.INSTANCE.lstUserToLstUserResponse(lstUser.getContent()))
                .build();
    }

    @Override
    @Transactional
    public void createUser(UserRequest userRequest) {
        User user = UserConverter.INSTANCE.userRequestToUser(userRequest);
        user.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        user.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        user.setDeleted(Boolean.FALSE);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserRequest userRequest, Long id) {
        Optional<User> currentUser = userRepository.findById(id);
        if (currentUser.isPresent() && Objects.nonNull(userRequest)) {
            User userToUpdate = currentUser.get();
            ReflectionUtils.copyNonNullFields(userRequest, userToUpdate);
            userToUpdate.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
            userRepository.save(userToUpdate);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> currentUser = userRepository.findById(id);
        if (currentUser.isPresent()) {
            User userToDelete = currentUser.get();
            userToDelete.setDeleted(Boolean.TRUE);
            userToDelete.setDeletedAt(DateTimeUtils.getCurrentDateTime());
            userRepository.save(userToDelete);
        }
    }

    @Override
    public UserResponse getUser(Long id) {
        return UserConverter.INSTANCE.userToUserResponse(getExistingUser(id));
    }

    @Override
    @Transactional
    public void hardDeleteUser(Long id) {
        User user = getExistingUser(id);
        userRepository.delete(user);
    }

    /**
     * Gets existing user.
     *
     * @param userId the user id
     * @return the existing user
     */
    public User getExistingUser(Long userId) {
        Optional<User> existUser = userRepository.findById(userId);
        if (existUser.isPresent()) {
            return existUser.get();
        } else throw new ResourceNotFoundException(UserServiceConst.USER, userId);
    }
}