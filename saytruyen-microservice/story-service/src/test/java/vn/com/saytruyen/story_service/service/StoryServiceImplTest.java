package vn.com.saytruyen.story_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vn.com.saytruyen.story_service.model.Story;
import vn.com.saytruyen.story_service.repository.StoryRepository;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryResponse;
import vn.com.saytruyen.story_service.service.impl.StoryServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Story service impl test.
 */
class StoryServiceImplTest {

    @Mock
    private StoryRepository storyRepository;

    @InjectMocks
    private StoryServiceImpl storyService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test create story.
     */
    @Test
    void testCreateStory() {
        StoryRequest storyRequest = new StoryRequest();
        storyService.createStory(storyRequest);
        verify(storyRepository, times(1)).save(any(Story.class));
    }

    /**
     * Test update story.
     */
    @Test
    void testUpdateStory() {
        StoryRequest storyRequest = new StoryRequest();
        Story story = new Story();
        when(storyRepository.findById(anyString())).thenReturn(Optional.of(story));

        storyService.updateStory(storyRequest, "1");

        verify(storyRepository, times(1)).save(any(Story.class));
    }

    /**
     * Test sort delete story.
     */
    @Test
    void testSortDeleteStory() {
        Story story = new Story();
        when(storyRepository.findById(anyString())).thenReturn(Optional.of(story));

        storyService.sortDeleteStory("1");

        assertTrue(story.isDeleted());
        verify(storyRepository, times(1)).save(story);
    }

    /**
     * Test hard delete story.
     */
    @Test
    void testHardDeleteStory() {
        storyService.hardDeleteStory("1");
        verify(storyRepository, times(1)).deleteById("1");
    }

    /**
     * Test get story.
     */
    @Test
    void testGetStory() {
        Story story = new Story();
        when(storyRepository.findById(anyString())).thenReturn(Optional.of(story));

        StoryResponse response = storyService.getStory("1");

        assertNotNull(response);
        verify(storyRepository, times(1)).findById("1");
    }
}
