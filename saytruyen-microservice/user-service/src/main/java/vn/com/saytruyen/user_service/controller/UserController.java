package vn.com.saytruyen.user_service.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.com.saytruyen.user_service.request.UserRequest;
import vn.com.saytruyen.user_service.response.UserResponse;

/**
 * The interface User controller.
 */
@RestController
@RequestMapping("/api/user-service/user")
public interface UserController {

    /**
     * Gets list user.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list user
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PageableResponse> getListUser(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                              @RequestParam(value = "pageSize", required = false) Integer pageSize);

    /**
     * Create user api response.
     *
     * @param userRequest the user request
     * @return the api response
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createUser(@RequestBody UserRequest userRequest);

    /**
     * Update user api response.
     *
     * @param userRequest the user request
     * @param id          the id
     * @return the api response
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateUser(@RequestBody UserRequest userRequest,
                                    @PathVariable Long id);

    /**
     * Delete user api response.
     *
     * @param id the id
     * @return the api response
     */
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteUser(@PathVariable Long id);

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<UserResponse> getUser(@PathVariable Long id);
}