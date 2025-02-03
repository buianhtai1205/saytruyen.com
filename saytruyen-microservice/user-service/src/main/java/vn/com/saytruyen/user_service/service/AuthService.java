package vn.com.saytruyen.user_service.service;

import vn.com.saytruyen.user_service.request.LoginRequest;
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
}
