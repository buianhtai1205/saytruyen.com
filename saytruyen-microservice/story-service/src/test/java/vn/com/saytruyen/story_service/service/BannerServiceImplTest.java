package vn.com.saytruyen.story_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vn.com.saytruyen.story_service.model.Banner;
import vn.com.saytruyen.story_service.repository.BannerRepository;
import vn.com.saytruyen.story_service.request.BannerRequest;
import vn.com.saytruyen.story_service.response.BannerResponse;
import vn.com.saytruyen.story_service.service.impl.BannerServiceImpl;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Banner service impl test.
 */
class BannerServiceImplTest {

    @Mock
    private BannerRepository bannerRepository;

    @InjectMocks
    private BannerServiceImpl bannerService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test get list banner.
     */
    @Test
    void testGetListBanner() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<Banner> page = new PageImpl<>(Collections.emptyList(), pageable, 0);
        when(bannerRepository.findAll(any(Pageable.class))).thenReturn(page);

        PageableResponse response = bannerService.getListBanner(1, 10);

        assertNotNull(response);
        assertEquals(1, response.getPageNumber());
        assertEquals(10, response.getPageSize());
        verify(bannerRepository, times(1)).findAll(any(Pageable.class));
    }

    /**
     * Test create banner.
     */
    @Test
    void testCreateBanner() {
        BannerRequest bannerRequest = new BannerRequest();
        bannerService.createBanner(bannerRequest);
        verify(bannerRepository, times(1)).save(any(Banner.class));
    }

    /**
     * Test update banner.
     */
    @Test
    void testUpdateBanner() {
        BannerRequest bannerRequest = new BannerRequest();
        Banner banner = new Banner();
        when(bannerRepository.findById(anyString())).thenReturn(Optional.of(banner));

        bannerService.updateBanner(bannerRequest, "1");

        verify(bannerRepository, times(1)).save(any(Banner.class));
    }

    /**
     * Test delete banner.
     */
    @Test
    void testDeleteBanner() {
        Banner banner = new Banner();
        when(bannerRepository.findById(anyString())).thenReturn(Optional.of(banner));

        bannerService.deleteBanner("1");

        assertTrue(banner.isDeleted());
        verify(bannerRepository, times(1)).save(banner);
    }

    /**
     * Test get banner.
     */
    @Test
    void testGetBanner() {
        Banner banner = new Banner();
        when(bannerRepository.findById(anyString())).thenReturn(Optional.of(banner));

        BannerResponse response = bannerService.getBanner("1");

        assertNotNull(response);
        verify(bannerRepository, times(1)).findById("1");
    }

    /**
     * Test hard delete banner.
     */
    @Test
    void testHardDeleteBanner() {
        Banner banner = new Banner();
        when(bannerRepository.findById(anyString())).thenReturn(Optional.of(banner));

        bannerService.hardDeleteBanner("1");

        verify(bannerRepository, times(1)).delete(banner);
    }
}
