package vn.com.saytruyen.story_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.story_service.converter.StoryConverter;
import vn.com.saytruyen.story_service.model.Story;
import vn.com.saytruyen.story_service.repository.StoryRepository;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryResponse;
import vn.com.saytruyen.story_service.service.StoryService;
import vn.com.saytruyen.story_service.utils.ReflectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Story service.
 */
@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public List<StoryResponse> getListStory() {
        return StoryConverter.INSTANCE.lstStoryToLstStoryResponse(storyRepository.findAll());
    }

    @Override
    public void createStory(StoryRequest storyRequest) {
        Story story = StoryConverter.INSTANCE.storyRequestToStory(storyRequest);
        story.setCreatedAt(LocalDateTime.now());
        story.setUpdatedAt(LocalDateTime.now());
        story.setNewChapAt(LocalDateTime.now());
        story.setPublishedAt(LocalDateTime.now());
        story.setDeleted(Boolean.FALSE);
        storyRepository.save(story);
    }

    @Override
    public void updateStory(StoryRequest storyRequest, String id) {
        Optional<Story> currentStory = storyRepository.findById(id);
        if (currentStory.isPresent() && Objects.nonNull(storyRequest)) {
            Story storyToUpdate = currentStory.get();
            ReflectionUtils.copyNonNullFields(storyRequest, storyToUpdate);
            storyToUpdate.setUpdatedAt(LocalDateTime.now());
            storyRepository.save(storyToUpdate);
        }
    }

    @Override
    public void sortDeleteStory(String id) {
        Optional<Story> currentStory = storyRepository.findById(id);
        if (currentStory.isPresent()) {
            Story storyToDelete = currentStory.get();
            storyToDelete.setDeleted(Boolean.TRUE);
            storyToDelete.setDeletedAt(LocalDateTime.now());
            storyRepository.save(storyToDelete);
        }
    }
}
