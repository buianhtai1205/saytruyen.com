package vn.com.saytruyen.story_service.service;

import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryResponse;

import java.util.List;

/**
 * The interface Story service.
 */
public interface StoryService {

    /**
     * Gets list story.
     *
     * @return the list story
     */
    List<StoryResponse> getListStory();

    /**
     * Create story.
     *
     * @param storyRequest the storyRequest
     */
    void createStory(StoryRequest storyRequest);

    /**
     * Update story.
     *
     * @param storyRequest the storyRequest
     * @param id           the id
     */
    void updateStory(StoryRequest storyRequest, String id);

    /**
     * Sort delete story.
     *
     * @param id the id
     */
    void sortDeleteStory(String id);
}
