package vn.com.saytruyen.admin_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.admin_service.constant.RequestType;
import vn.com.saytruyen.admin_service.producer.KafkaProducerService;
import vn.com.saytruyen.admin_service.request.BannerRequest;
import vn.com.saytruyen.admin_service.request.PageInfoRequest;
import vn.com.saytruyen.admin_service.request.UpdateBannerRequest;
import vn.com.saytruyen.admin_service.response.BannerResponse;
import vn.com.saytruyen.admin_service.service.BannerService;

import java.util.concurrent.TimeUnit;

/**
 * The type Banner service.
 */
@Service
@Slf4j
public class BannerServiceImpl implements BannerService {

    private final KafkaProducerService producerService;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Instantiates a new Story service.
     *
     * @param producerService the producer service
     * @param redisTemplate   the redis template
     */
    @Autowired
    public BannerServiceImpl(KafkaProducerService producerService,
                             RedisTemplate<String, Object> redisTemplate) {
        this.producerService = producerService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Object getListBanner(Integer pageNumber, Integer pageSize) {
        // Check cache
        String cacheKey = "banners:" + pageNumber + ":" + pageSize;
        Object cachedResponse = redisTemplate.opsForValue().get(cacheKey);

        if (cachedResponse != null) {
            log.info("Get list banner from cache: {}", cacheKey);
            return cachedResponse;
        }

        // Make request object
        PageInfoRequest request = new PageInfoRequest();
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);

        // Send message with topic, request, and response type
        log.info("Get list banner from story-service: {}", request);
        Object response = producerService.sendMessage(
                request,
                RequestType.GET_ALL_BANNERS,
                PageableResponse.class
        );

        // Save cache
        log.info("Save list banner to cache: {}", cacheKey);
        redisTemplate.opsForValue().set(cacheKey, response, 10, TimeUnit.MINUTES);

        return response;
    }

    @Override
    public void createBanner(BannerRequest bannerRequest) {
        producerService.sendMessageWithoutReply(bannerRequest, RequestType.CREATE_NEW_BANNER);
    }

    @Override
    public void updateBanner(BannerRequest bannerRequest, String id) {
        UpdateBannerRequest request = UpdateBannerRequest.builder()
                .bannerId(id)
                .bannerRequest(bannerRequest)
                .build();
        producerService.sendMessageWithoutReply(request, RequestType.UPDATE_BANNER);
    }

    @Override
    public void deleteBanner(String id) {
        producerService.sendMessageWithoutReply(id, RequestType.DELETE_BANNER);
    }

    @Override
    public Object getBanner(String id) {
        // Check cache
        String cacheKey = "banner:" + id;
        Object cachedResponse = redisTemplate.opsForValue().get(cacheKey);

        if (cachedResponse != null) {
            log.info("Get banner from cache: {}", cacheKey);
            return cachedResponse;
        }

        Object response = producerService.sendMessage(id, RequestType.GET_BANNER_BY_ID, BannerResponse.class);

        // Save cache
        log.info("Save story to cache: {}", cacheKey);
        redisTemplate.opsForValue().set(cacheKey, response, 10, TimeUnit.MINUTES);

        return response;
    }
}
