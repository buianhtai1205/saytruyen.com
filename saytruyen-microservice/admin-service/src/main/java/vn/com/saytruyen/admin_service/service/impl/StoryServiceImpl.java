package vn.com.saytruyen.admin_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.admin_service.constant.RequestType;
import vn.com.saytruyen.admin_service.producer.KafkaProducerService;
import vn.com.saytruyen.admin_service.request.PageInfoRequest;
import vn.com.saytruyen.admin_service.request.StoryRequest;
import vn.com.saytruyen.admin_service.request.UpdateStoryRequest;
import vn.com.saytruyen.admin_service.response.StoryResponse;
import vn.com.saytruyen.admin_service.service.StoryService;

import java.util.concurrent.TimeUnit;

/**
 * The type Story service.
 */
@Service
@Slf4j
public class StoryServiceImpl implements StoryService {

    private final KafkaProducerService producerService;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Instantiates a new Story service.
     *
     * @param producerService the producer service
     * @param redisTemplate   the redis template
     */
    @Autowired
    public StoryServiceImpl(KafkaProducerService producerService,
                            RedisTemplate<String, Object> redisTemplate) {
        this.producerService = producerService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Object getListStory(Integer pageNumber, Integer pageSize) {
        // Check cache
        String cacheKey = "stories:" + pageNumber + ":" + pageSize;
        Object cachedResponse = redisTemplate.opsForValue().get(cacheKey);

        if (cachedResponse != null) {
            log.info("Get list stories from cache: {}", cacheKey);
            return cachedResponse;
        }

        // Make request object
        PageInfoRequest request = new PageInfoRequest();
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);

        // Send message with topic, request, and response type
        log.info("Get list stories from story-service: {}", request);
        Object response = producerService.sendMessage(
                request,
                RequestType.GET_ALL_STORIES,
                PageableResponse.class
        );

        // Save cache
        log.info("Save list stories to cache: {}", cacheKey);
        redisTemplate.opsForValue().set(cacheKey, response, 10, TimeUnit.MINUTES);

        return response;
    }

    @Override
    public void createStory(StoryRequest storyRequest) {
        producerService.sendMessageWithoutReply(storyRequest, RequestType.CREATE_NEW_STORY);
    }

    @Override
    public void updateStory(StoryRequest storyRequest, String id) {
        UpdateStoryRequest request = UpdateStoryRequest.builder()
                .storyId(id)
                .storyRequest(storyRequest)
                .build();
        producerService.sendMessageWithoutReply(request, RequestType.UPDATE_STORY);
    }

    @Override
    public void deleteStory(String id) {
        producerService.sendMessageWithoutReply(id, RequestType.DELETE_STORY);
    }

    @Override
    public Object getStory(String id) {
        // Check cache
        String cacheKey = "story:" + id;
        Object cachedResponse = redisTemplate.opsForValue().get(cacheKey);

        if (cachedResponse != null) {
            log.info("Get story from cache: {}", cacheKey);
            return cachedResponse;
        }

        Object response = producerService.sendMessage(id, RequestType.GET_STORY_BY_ID, StoryResponse.class);

        // Save cache
        log.info("Save story to cache: {}", cacheKey);
        redisTemplate.opsForValue().set(cacheKey, response, 10, TimeUnit.MINUTES);

        return response;
    }
}
