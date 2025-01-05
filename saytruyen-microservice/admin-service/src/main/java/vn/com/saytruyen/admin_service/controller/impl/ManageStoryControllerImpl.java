package vn.com.saytruyen.admin_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.admin_service.controller.ManageStoryController;
import vn.com.saytruyen.admin_service.service.StoryService;

/**
 * The type Manage story controller.
 */
@Component
public class ManageStoryControllerImpl implements ManageStoryController {

    private final StoryService storyService;

    /**
     * Instantiates a new Manage story controller.
     *
     * @param storyService the story service
     */
    @Autowired
    public ManageStoryControllerImpl(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public ApiResponse<Object> getListStory(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>(storyService.getListStory(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Object> createStory(Object storyRequest) {
        storyService.createStory(storyRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Object> updateStory(Object storyRequest, String id) {
        storyService.updateStory(storyRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Object> deleteStory(String id) {
        storyService.deleteStory(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Object> getStory(String id) {
        return new ApiResponse<>(storyService.getStory(id));
    }
}
