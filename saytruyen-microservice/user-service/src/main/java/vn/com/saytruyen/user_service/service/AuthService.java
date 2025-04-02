package vn.com.saytruyen.user_service.service;

import vn.com.saytruyen.user_service.request.LoginRequest;
import vn.com.saytruyen.user_service.request.UserSignUpRequest;
import vn.com.saytruyen.user_service.response.LoginResponse;

/**
 * The interface Auth service.
 */
public interface AuthService {

    /**
     * Login login response.
     *
     * @param request the request
     * @return the login response
     */
    LoginResponse login(LoginRequest request);

    /**
     * Sign up.
     *
     * @param request the request
     */
    void signUp(UserSignUpRequest request);

    /**
     * Logout.
     *
     * @param token the token
     */
    void logout(String token);

    /**
     * Add token to black list.
     *
     * @param token        the token
     * @param timeToExpire the time to expire
     */
    void addTokenToBlackList(String token, long timeToExpire);

    /**
     * Is token in black list boolean.
     *
     * @param token the token
     * @return the boolean
     */
    boolean isTokenInBlackList(String token);

    /**
     * User info object.
     *
     * @return the object
     */
    Object userInfo();

    /**
     * Refresh token login response.
     *
     * @param refreshToken the refresh token
     * @return the login response
     */
    LoginResponse refreshToken(String refreshToken);
}
