package vn.com.saytruyen.user_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.user_service.controller.UserController;
import vn.com.saytruyen.user_service.request.UserRequest;
import vn.com.saytruyen.user_service.response.UserResponse;
import vn.com.saytruyen.user_service.service.UserService;

/**
 * The type User controller.
 */
@Component
public class UserControllerImpl implements UserController {

    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ApiResponse<PageableResponse> getListUser(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>(userService.getListUser(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> createUser(UserRequest userRequest) {
        userService.createUser(userRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateUser(UserRequest userRequest, Long id) {
        userService.updateUser(userRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteUser(Long id) {
        userService.deleteUser(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<UserResponse> getUser(Long id) {
        return new ApiResponse<>(userService.getUser(id));
    }
}