package vn.com.saytruyen.admin_service.service;

import vn.com.saytruyen.admin_service.request.StoryRequest;

/**
 * The interface Story service.
 */
public interface StoryService {

    /**
     * Gets list story.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list story
     */
    Object getListStory(Integer pageNumber, Integer pageSize);

    /**
     * Create story.
     *
     * @param storyRequest the story request
     */
    void createStory(StoryRequest storyRequest);

    /**
     * Update story.
     *
     * @param storyRequest the story request
     * @param id           the id
     */
    void updateStory(StoryRequest storyRequest, String id);

    /**
     * Delete story.
     *
     * @param id the id
     */
    void deleteStory(String id);

    /**
     * Gets story.
     *
     * @param id the id
     * @return the story
     */
    Object getStory(String id);
}
