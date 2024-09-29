package vn.com.saytruyen.story_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryResponse;

import java.util.List;

/**
 * The interface Story controller.
 */
@RestController
@RequestMapping("/api/story")
public interface StoryController {

    /**
     * Gets list story.
     *
     * @return the list story
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<StoryResponse> getListStory();

    /**
     * Create story.
     *
     * @param storyRequest the storyRequest
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void createStory(@RequestBody StoryRequest storyRequest);

    /**
     * Update story.
     *
     * @param storyRequest the storyRequest
     * @param id           the id
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateStory(@RequestBody StoryRequest storyRequest, @PathVariable String id);

    /**
     * Delete story.
     *
     * @param id the id
     */
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteStory(@PathVariable String id);
}
