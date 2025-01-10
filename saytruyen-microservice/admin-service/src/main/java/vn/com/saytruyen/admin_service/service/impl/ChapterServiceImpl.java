package vn.com.saytruyen.admin_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.admin_service.constant.RequestType;
import vn.com.saytruyen.admin_service.producer.KafkaProducerService;
import vn.com.saytruyen.admin_service.request.ChapterRequest;
import vn.com.saytruyen.admin_service.request.GetAllChapterRequest;
import vn.com.saytruyen.admin_service.request.PageInfoRequest;
import vn.com.saytruyen.admin_service.request.UpdateChapterRequest;
import vn.com.saytruyen.admin_service.response.BannerResponse;
import vn.com.saytruyen.admin_service.service.ChapterService;

import java.util.concurrent.TimeUnit;

/**
 * The type Chapter service.
 */
@Service
@Slf4j
public class ChapterServiceImpl implements ChapterService {

    private final KafkaProducerService producerService;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Instantiates a new Story service.
     *
     * @param producerService the producer service
     * @param redisTemplate   the redis template
     */
    @Autowired
    public ChapterServiceImpl(KafkaProducerService producerService,
                              RedisTemplate<String, Object> redisTemplate) {
        this.producerService = producerService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void createChapter(ChapterRequest chapterRequest) {
        producerService.sendMessageWithoutReply(chapterRequest, RequestType.CREATE_NEW_CHAPTER);
    }

    @Override
    public void updateChapter(ChapterRequest chapterRequest, String chapterId) {
        UpdateChapterRequest request = UpdateChapterRequest.builder()
                .chapterId(chapterId)
                .chapterRequest(chapterRequest)
                .build();
        producerService.sendMessageWithoutReply(request, RequestType.UPDATE_CHAPTER);
    }

    @Override
    public void deleteChapter(String chapterId) {
        producerService.sendMessageWithoutReply(chapterId, RequestType.DELETE_CHAPTER);
    }

    @Override
    public Object getChapter(String chapterId) {
        // Check cache
        String cacheKey = "chapter:" + chapterId;
        Object cachedResponse = redisTemplate.opsForValue().get(cacheKey);

        if (cachedResponse != null) {
            log.info("Get chapter from cache: {}", cacheKey);
            return cachedResponse;
        }

        Object response = producerService.sendMessage(chapterId, RequestType.GET_CHAPTER_BY_ID, BannerResponse.class);

        // Save cache
        log.info("Save chapter to cache: {}", cacheKey);
        redisTemplate.opsForValue().set(cacheKey, response, 10, TimeUnit.MINUTES);

        return response;
    }

    @Override
    public Object getAllChapter(Integer pageNumber, Integer pageSize, String storyId) {
        // Check cache
        String cacheKey = "chapters:" + pageNumber + ":" + pageSize;
        Object cachedResponse = redisTemplate.opsForValue().get(cacheKey);

        if (cachedResponse != null) {
            log.info("Get list chapter from cache: {}", cacheKey);
            return cachedResponse;
        }

        // Make request object
        PageInfoRequest request = new PageInfoRequest();
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);

        GetAllChapterRequest getAllChapterRequest = GetAllChapterRequest.builder()
                .storyId(storyId)
                .pageInfoRequest(request)
                .build();

        // Send message with topic, request, and response type
        log.info("Get list chapter from story-service: {}", request);
        Object response = producerService.sendMessage(
                getAllChapterRequest,
                RequestType.GET_ALL_CHAPTERS,
                PageableResponse.class
        );

        // Save cache
        log.info("Save list chapter to cache: {}", cacheKey);
        redisTemplate.opsForValue().set(cacheKey, response, 10, TimeUnit.MINUTES);

        return response;
    }
}
