package vn.com.saytruyen.user_service.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.saytruyen.user_service.request.LoginRequest;
import vn.com.saytruyen.user_service.request.UserSignUpRequest;
import vn.com.saytruyen.user_service.response.LoginResponse;

/**
 * The interface Auth controller.
 */
@RestController
@RequestMapping("/api/user-service/auth")
public interface AuthController {

    /**
     * Login api response.
     *
     * @param request the request
     * @return the api response
     */
    @PostMapping("/login")
    ApiResponse<LoginResponse> login(@RequestBody LoginRequest request);

    /**
     * Refresh token api response.
     *
     * @param refreshToken the refresh token
     * @return the api response
     */
    @PostMapping("/refresh-token")
    ApiResponse<LoginResponse> refreshToken(@RequestParam String refreshToken);

    /**
     * Logout api response.
     *
     * @param token the token
     * @return the api response
     */
    @PostMapping("/logout")
    ApiResponse<Boolean> logout(@RequestParam String token);

    /**
     * Sign up api response.
     *
     * @param request the request
     * @return the api response
     */
    @PostMapping("/sign-up")
    ApiResponse<Boolean> signUp(@RequestBody UserSignUpRequest request);
}
