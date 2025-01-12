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
import vn.com.saytruyen.admin_service.request.ChapterRequest;
import vn.com.saytruyen.admin_service.request.GetAllChapterRequest;
import vn.com.saytruyen.admin_service.request.UpdateChapterRequest;
import vn.com.saytruyen.admin_service.response.BannerResponse;
import vn.com.saytruyen.admin_service.service.impl.ChapterServiceImpl;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Chapter service impl test.
 */
class ChapterServiceImplTest {

    @Mock
    private KafkaProducerService producerService;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @InjectMocks
    private ChapterServiceImpl chapterService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    /**
     * Test create chapter.
     */
    @Test
    void testCreateChapter() {
        ChapterRequest chapterRequest = new ChapterRequest();
        chapterService.createChapter(chapterRequest);
        verify(producerService, times(1)).sendMessageWithoutReply(chapterRequest, RequestType.CREATE_NEW_CHAPTER);
    }

    /**
     * Test update chapter.
     */
    @Test
    void testUpdateChapter() {
        ChapterRequest chapterRequest = new ChapterRequest();
        chapterService.updateChapter(chapterRequest, "1");
        verify(producerService, times(1)).sendMessageWithoutReply(any(UpdateChapterRequest.class), eq(RequestType.UPDATE_CHAPTER));
    }

    /**
     * Test delete chapter.
     */
    @Test
    void testDeleteChapter() {
        chapterService.deleteChapter("1");
        verify(producerService, times(1)).sendMessageWithoutReply("1", RequestType.DELETE_CHAPTER);
    }

    /**
     * Test get chapter.
     */
    @Test
    void testGetChapter() {
        String chapterId = "1";
        when(valueOperations.get("chapter:" + chapterId)).thenReturn(null);
        when(producerService.sendMessage(anyString(), eq(RequestType.GET_CHAPTER_BY_ID), eq(BannerResponse.class))).thenReturn(new BannerResponse());

        Object response = chapterService.getChapter(chapterId);

        assertNotNull(response);
        verify(valueOperations, times(1)).get("chapter:" + chapterId);
        verify(producerService, times(1)).sendMessage(chapterId, RequestType.GET_CHAPTER_BY_ID, BannerResponse.class);
        verify(valueOperations, times(1)).set(eq("chapter:" + chapterId), any(), eq(10L), eq(TimeUnit.MINUTES));
    }

    /**
     * Test get all chapter.
     */
    @Test
    void testGetAllChapter() {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String storyId = "1";
        when(valueOperations.get("chapters:" + pageNumber + ":" + pageSize)).thenReturn(null);
        when(producerService.sendMessage(any(GetAllChapterRequest.class), eq(RequestType.GET_ALL_CHAPTERS), eq(PageableResponse.class))).thenReturn(new PageableResponse());

        Object response = chapterService.getAllChapter(pageNumber, pageSize, storyId);

        assertNotNull(response);
        verify(valueOperations, times(1)).get("chapters:" + pageNumber + ":" + pageSize);
        verify(producerService, times(1)).sendMessage(any(GetAllChapterRequest.class), eq(RequestType.GET_ALL_CHAPTERS), eq(PageableResponse.class));
        verify(valueOperations, times(1)).set(eq("chapters:" + pageNumber + ":" + pageSize), any(), eq(10L), eq(TimeUnit.MINUTES));
    }
}
