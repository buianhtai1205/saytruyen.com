package vn.com.saytruyen.admin_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.admin_service.producer.KafkaProducerService;
import vn.com.saytruyen.admin_service.request.BannerRequest;
import vn.com.saytruyen.admin_service.service.BannerService;

/**
 * The type Banner service.
 */
@Service
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
        return null;
    }

    @Override
    public void createBanner(BannerRequest bannerRequest) {

    }

    @Override
    public void updateBanner(BannerRequest bannerRequest, String id) {

    }

    @Override
    public void deleteBanner(String id) {

    }

    @Override
    public Object getBanner(String id) {
        return null;
    }
}
