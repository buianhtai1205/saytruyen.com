package vn.com.saytruyen.user_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.user_service.request.UserRequest;
import vn.com.saytruyen.user_service.response.UserResponse;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Gets list user.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list user
     */
    PageableResponse getListUser(Integer pageNumber, Integer pageSize);

    /**
     * Create user.
     *
     * @param userRequest the user request
     */
    void createUser(UserRequest userRequest);

    /**
     * Update user.
     *
     * @param userRequest the user request
     * @param userId      the user id
     */
    void updateUser(UserRequest userRequest, Long userId);

    /**
     * Delete user.
     *
     * @param id the id
     */
    void deleteUser(Long id);

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    UserResponse getUser(Long id);

    /**
     * Hard delete user.
     *
     * @param id the id
     */
    void hardDeleteUser(Long id);
}