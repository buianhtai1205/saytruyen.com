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
import vn.com.saytruyen.admin_service.request.BannerRequest;
import vn.com.saytruyen.admin_service.request.PageInfoRequest;
import vn.com.saytruyen.admin_service.request.UpdateBannerRequest;
import vn.com.saytruyen.admin_service.response.BannerResponse;
import vn.com.saytruyen.admin_service.service.impl.BannerServiceImpl;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Banner service impl test.
 */
class BannerServiceImplTest {

    @Mock
    private KafkaProducerService producerService;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @InjectMocks
    private BannerServiceImpl bannerService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    /**
     * Test create banner.
     */
    @Test
    void testCreateBanner() {
        BannerRequest bannerRequest = new BannerRequest();
        bannerService.createBanner(bannerRequest);
        verify(producerService, times(1)).sendMessageWithoutReply(bannerRequest, RequestType.CREATE_NEW_BANNER);
    }

    /**
     * Test update banner.
     */
    @Test
    void testUpdateBanner() {
        BannerRequest bannerRequest = new BannerRequest();
        bannerService.updateBanner(bannerRequest, "1");
        verify(producerService, times(1)).sendMessageWithoutReply(any(UpdateBannerRequest.class), eq(RequestType.UPDATE_BANNER));
    }

    /**
     * Test delete banner.
     */
    @Test
    void testDeleteBanner() {
        bannerService.deleteBanner("1");
        verify(producerService, times(1)).sendMessageWithoutReply("1", RequestType.DELETE_BANNER);
    }

    /**
     * Test get banner.
     */
    @Test
    void testGetBanner() {
        String bannerId = "1";
        when(valueOperations.get("banner:" + bannerId)).thenReturn(null);
        when(producerService.sendMessage(anyString(), eq(RequestType.GET_BANNER_BY_ID), eq(BannerResponse.class))).thenReturn(new BannerResponse());

        Object response = bannerService.getBanner(bannerId);

        assertNotNull(response);
        verify(valueOperations, times(1)).get("banner:" + bannerId);
        verify(producerService, times(1)).sendMessage(bannerId, RequestType.GET_BANNER_BY_ID, BannerResponse.class);
        verify(valueOperations, times(1)).set(eq("banner:" + bannerId), any(), eq(10L), eq(TimeUnit.MINUTES));
    }

    /**
     * Test get list banner.
     */
    @Test
    void testGetListBanner() {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        when(valueOperations.get("banners:" + pageNumber + ":" + pageSize)).thenReturn(null);
        when(producerService.sendMessage(any(PageInfoRequest.class), eq(RequestType.GET_ALL_BANNERS), eq(PageableResponse.class))).thenReturn(new PageableResponse());

        Object response = bannerService.getListBanner(pageNumber, pageSize);

        assertNotNull(response);
        verify(valueOperations, times(1)).get("banners:" + pageNumber + ":" + pageSize);
        verify(producerService, times(1)).sendMessage(any(PageInfoRequest.class), eq(RequestType.GET_ALL_BANNERS), eq(PageableResponse.class));
        verify(valueOperations, times(1)).set(eq("banners:" + pageNumber + ":" + pageSize), any(), eq(10L), eq(TimeUnit.MINUTES));
    }
}
