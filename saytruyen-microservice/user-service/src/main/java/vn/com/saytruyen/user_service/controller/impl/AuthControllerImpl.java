package vn.com.saytruyen.user_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.user_service.controller.AuthController;
import vn.com.saytruyen.user_service.request.LoginRequest;
import vn.com.saytruyen.user_service.request.UserSignUpRequest;
import vn.com.saytruyen.user_service.response.LoginResponse;
import vn.com.saytruyen.user_service.service.AuthService;

/**
 * The type Auth controller.
 */
@Component
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    /**
     * Instantiates a new Auth controller.
     *
     * @param authService the auth service
     */
    @Autowired
    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ApiResponse<LoginResponse> login(LoginRequest request) {
        return new ApiResponse<>(authService.login(request));
    }

    @Override
    public ApiResponse<LoginResponse> refreshToken(String refreshToken) {
        return new ApiResponse<>(authService.refreshToken(refreshToken));
    }

    @Override
    public ApiResponse<Boolean> logout(String token) {
        authService.logout(token);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> signUp(UserSignUpRequest request) {
        authService.signUp(request);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<?> userInfo() {
        return new ApiResponse<>(authService.userInfo());
    }
}
