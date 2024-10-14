package vn.com.saytruyen.story_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.story_service.converter.StoryConverter;
import vn.com.saytruyen.story_service.model.Story;
import vn.com.saytruyen.story_service.repository.StoryRepository;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryResponse;
import vn.com.saytruyen.story_service.service.StoryService;

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
        story.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        story.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        story.setNewChapAt(DateTimeUtils.getCurrentDateTime());
        story.setPublishedAt(DateTimeUtils.getCurrentDateTime());
        story.setDeleted(Boolean.FALSE);
        storyRepository.save(story);
    }

    @Override
    public void updateStory(StoryRequest storyRequest, String id) {
        Optional<Story> currentStory = storyRepository.findById(id);
        if (currentStory.isPresent() && Objects.nonNull(storyRequest)) {
            Story storyToUpdate = currentStory.get();
            ReflectionUtils.copyNonNullFields(storyRequest, storyToUpdate);
            storyToUpdate.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
            storyRepository.save(storyToUpdate);
        }
    }

    @Override
    public void sortDeleteStory(String id) {
        Optional<Story> currentStory = storyRepository.findById(id);
        if (currentStory.isPresent()) {
            Story storyToDelete = currentStory.get();
            storyToDelete.setDeleted(Boolean.TRUE);
            storyToDelete.setDeletedAt(DateTimeUtils.getCurrentDateTime());
            storyRepository.save(storyToDelete);
        }
    }
}
