package vn.com.saytruyen.story_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.story_service.controller.StoryController;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryCrawlResponse;
import vn.com.saytruyen.story_service.response.StoryResponse;
import vn.com.saytruyen.story_service.service.StoryService;

import java.util.List;

/**
 * The type Story controller.
 */
@Component
public class StoryControllerImpl implements StoryController {

    @Autowired
    private StoryService storyService;

    @Override
    public ApiResponse<PageableResponse> getListStory(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>(storyService.getListStory(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> createStory(StoryRequest storyRequest) {
        storyService.createStory(storyRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateStory(StoryRequest storyRequest, String id) {
        storyService.updateStory(storyRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteStory(String id) {
        storyService.sortDeleteStory(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<List<StoryCrawlResponse>> getListStoryCrawl() {
        return new ApiResponse<>(storyService.getListStoryCrawl());
    }

    @Override
    public ApiResponse<StoryResponse> getStory(String id) {
        return new ApiResponse<>(storyService.getStory(id));
    }

    @Override
    public ApiResponse<Integer> deleteStoryCrawl() {
        return new ApiResponse<>(storyService.deleteStoryCrawl());
    }
}
