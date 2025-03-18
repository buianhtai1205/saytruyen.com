package vn.com.saytruyen.story_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryCrawlResponse;
import vn.com.saytruyen.story_service.response.StoryResponse;

import java.util.List;

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
    PageableResponse getListStory(Integer pageNumber, Integer pageSize);

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

    /**
     * Hard delete story.
     *
     * @param id the id
     */
    void hardDeleteStory(String id);

    /**
     * Gets list story crawl.
     *
     * @return the list story crawl
     */
    List<StoryCrawlResponse> getListStoryCrawl();

    /**
     * Gets story.
     *
     * @param id the id
     * @return the story
     */
    StoryResponse getStory(String id);

    /**
     * Delete story crawl integer.
     *
     * @return the integer
     */
    Integer deleteStoryCrawl();

    /**
     * Gets random story.
     *
     * @return the random story
     */
    List<StoryResponse> getRandomStory();
}
