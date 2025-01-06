package vn.com.saytruyen.story_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.story_service.constant.StoryServiceConst;
import vn.com.saytruyen.story_service.converter.StoryConverter;
import vn.com.saytruyen.story_service.model.Story;
import vn.com.saytruyen.story_service.repository.StoryRepository;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryCrawlResponse;
import vn.com.saytruyen.story_service.response.StoryResponse;
import vn.com.saytruyen.story_service.service.StoryService;

import java.time.LocalDate;
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
    public PageableResponse getListStory(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? StoryServiceConst.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? StoryServiceConst.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Story> lstStory = storyRepository.findAll(pageable);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lstStory.getTotalPages())
                .totalElements(lstStory.getTotalElements())
                .data(StoryConverter.INSTANCE.lstStoryToLstStoryResponse(lstStory.getContent()))
                .build();
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

    @Override
    public void hardDeleteStory(String id) {
        storyRepository.deleteById(id);
    }

    @Override
    public List<StoryCrawlResponse> getListStoryCrawl() {
        return StoryConverter.INSTANCE.lstStoryToLstStoryCrawlResponse(storyRepository.findAll());
    }

    @Override
    public StoryResponse getStory(String id) {
        return StoryConverter.INSTANCE.storyToStoryResponse(getExistingStory(id));
    }

    @Override
    public Integer deleteStoryCrawl() {
        List<Story> lstDeleteStory = storyRepository.findByNowCreatedAt(LocalDate.now().atStartOfDay());
        storyRepository.deleteAll(lstDeleteStory);
        return lstDeleteStory.size();
    }

    /**
     * Gets existing story.
     *
     * @param storyId the story id
     * @return the existing story
     */
    public Story getExistingStory(String storyId) {
        Optional<Story> existStory = storyRepository.findById(storyId);
        if (existStory.isPresent()) {
            return existStory.get();
        } else throw new ResourceNotFoundException(StoryServiceConst.STORY_VN, storyId);
    }
}
