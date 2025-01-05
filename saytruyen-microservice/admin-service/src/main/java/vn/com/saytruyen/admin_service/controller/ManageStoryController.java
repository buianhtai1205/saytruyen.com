package vn.com.saytruyen.admin_service.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
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

/**
 * The interface Manage story controller.
 */
@RestController
@RequestMapping("/api/admin-service/story")
public interface ManageStoryController {

    /**
     * Gets list story.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list story
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Object> getListStory(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize);

    /**
     * Create story object.
     *
     * @param storyRequest the story request
     * @return the object
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Object> createStory(@RequestBody Object storyRequest);

    /**
     * Update story object.
     *
     * @param storyRequest the story request
     * @param id           the id
     * @return the object
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Object> updateStory(@RequestBody Object storyRequest, @PathVariable String id);

    /**
     * Delete story object.
     *
     * @param id the id
     * @return the object
     */
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Object> deleteStory(@PathVariable String id);

    /**
     * Gets story.
     *
     * @param id the id
     * @return the story
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Object> getStory(@PathVariable String id);
}
