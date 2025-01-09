package vn.com.saytruyen.admin_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.admin_service.producer.KafkaProducerService;
import vn.com.saytruyen.admin_service.request.ChapterRequest;
import vn.com.saytruyen.admin_service.service.ChapterService;

/**
 * The type Chapter service.
 */
@Service
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

    }

    @Override
    public void updateChapter(ChapterRequest chapterRequest, String chapterId) {

    }

    @Override
    public void deleteChapter(String chapterId) {

    }

    @Override
    public Object getChapter(String chapterId) {
        return null;
    }

    @Override
    public Object getAllChapter(Integer pageNumber, Integer pageSize, String storyId) {
        return null;
    }
}
