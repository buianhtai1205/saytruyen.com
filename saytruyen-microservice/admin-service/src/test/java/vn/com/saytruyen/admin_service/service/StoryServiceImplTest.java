package vn.com.saytruyen.admin_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import vn.com.saytruyen.admin_service.constant.RequestType;
import vn.com.saytruyen.admin_service.producer.KafkaProducerService;
import vn.com.saytruyen.admin_service.request.PageInfoRequest;
import vn.com.saytruyen.admin_service.request.StoryRequest;
import vn.com.saytruyen.admin_service.request.UpdateStoryRequest;
import vn.com.saytruyen.admin_service.response.StoryResponse;
import vn.com.saytruyen.admin_service.service.impl.StoryServiceImpl;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Story service impl test.
 */
class StoryServiceImplTest {

    @Mock
    private KafkaProducerService producerService;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @InjectMocks
    private StoryServiceImpl storyService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    /**
     * Test create story.
     */
    @Test
    void testCreateStory() {
        StoryRequest storyRequest = new StoryRequest();
        storyService.createStory(storyRequest);
        verify(producerService, times(1)).sendMessageWithoutReply(storyRequest, RequestType.CREATE_NEW_STORY);
    }

    /**
     * Test update story.
     */
    @Test
    void testUpdateStory() {
        StoryRequest storyRequest = new StoryRequest();
        storyService.updateStory(storyRequest, "1");
        verify(producerService, times(1)).sendMessageWithoutReply(any(UpdateStoryRequest.class), eq(RequestType.UPDATE_STORY));
    }

    /**
     * Test delete story.
     */
    @Test
    void testDeleteStory() {
        storyService.deleteStory("1");
        verify(producerService, times(1)).sendMessageWithoutReply("1", RequestType.DELETE_STORY);
    }

    /**
     * Test get story.
     */
    @Test
    void testGetStory() {
        String storyId = "1";
        when(valueOperations.get("story:" + storyId)).thenReturn(null);
        when(producerService.sendMessage(anyString(), eq(RequestType.GET_STORY_BY_ID), eq(StoryResponse.class))).thenReturn(new StoryResponse());

        Object response = storyService.getStory(storyId);

        assertNotNull(response);
        verify(valueOperations, times(1)).get("story:" + storyId);
        verify(producerService, times(1)).sendMessage(storyId, RequestType.GET_STORY_BY_ID, StoryResponse.class);
        verify(valueOperations, times(1)).set(eq("story:" + storyId), any(), eq(10L), eq(TimeUnit.MINUTES));
    }

    /**
     * Test get list story.
     */
    @Test
    void testGetListStory() {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        when(valueOperations.get("stories:" + pageNumber + ":" + pageSize)).thenReturn(null);
        when(producerService.sendMessage(any(PageInfoRequest.class), eq(RequestType.GET_ALL_STORIES), eq(PageableResponse.class))).thenReturn(new PageableResponse());

        Object response = storyService.getListStory(pageNumber, pageSize);

        assertNotNull(response);
        verify(valueOperations, times(1)).get("stories:" + pageNumber + ":" + pageSize);
        verify(producerService, times(1)).sendMessage(any(PageInfoRequest.class), eq(RequestType.GET_ALL_STORIES), eq(PageableResponse.class));
        verify(valueOperations, times(1)).set(eq("stories:" + pageNumber + ":" + pageSize), any(), eq(10L), eq(TimeUnit.MINUTES));
    }
}
