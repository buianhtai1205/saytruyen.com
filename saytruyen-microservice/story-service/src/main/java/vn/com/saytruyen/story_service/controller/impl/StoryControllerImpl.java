package vn.com.saytruyen.story_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.story_service.controller.StoryController;
import vn.com.saytruyen.story_service.request.StoryRequest;
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
    public ApiResponse<List<StoryResponse>> getListStory() {
        return new ApiResponse<>(storyService.getListStory());
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
}
