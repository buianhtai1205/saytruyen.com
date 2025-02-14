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
import vn.com.saytruyen.user_service.request.LevelRequest;
import vn.com.saytruyen.user_service.response.LevelResponse;

/**
 * The interface Level controller.
 */
@RestController
@RequestMapping("/api/user-service/level")
public interface LevelController {

    /**
     * Gets list level.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list level
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PageableResponse> getListLevel(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                               @RequestParam(value = "pageSize", required = false) Integer pageSize);

    /**
     * Create level api response.
     *
     * @param levelRequest the level request
     * @return the api response
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createLevel(@RequestBody LevelRequest levelRequest);

    /**
     * Update level api response.
     *
     * @param levelRequest the level request
     * @param id           the id
     * @return the api response
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateLevel(@RequestBody LevelRequest levelRequest,
                                     @PathVariable Long id);

    /**
     * Delete level api response.
     *
     * @param id the id
     * @return the api response
     */
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteLevel(@PathVariable Long id);

    /**
     * Gets level.
     *
     * @param id the id
     * @return the level
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<LevelResponse> getLevel(@PathVariable Long id);
}