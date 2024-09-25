package vn.com.saytruyen.story_service.controller.impl;

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
    public List<StoryResponse> getListStory() {
        return storyService.getListStory();
    }

    @Override
    public void createStory(StoryRequest storyRequest) {
        storyService.createStory(storyRequest);
    }

    @Override
    public void updateStory(StoryRequest storyRequest, String id) {
        storyService.updateStory(storyRequest, id);
    }

    @Override
    public void deleteStory(String id) {
        storyService.sortDeleteStory(id);
    }
}
