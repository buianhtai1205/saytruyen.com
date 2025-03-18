package vn.com.saytruyen.story_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.story_service.request.BannerRequest;
import vn.com.saytruyen.story_service.response.BannerResponse;

import java.util.List;

/**
 * The interface Banner service.
 */
public interface BannerService {
    /**
     * Gets list banner.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list banner
     */
    PageableResponse getListBanner(Integer pageNumber, Integer pageSize);

    /**
     * Create banner.
     *
     * @param bannerRequest the banner request
     */
    void createBanner(BannerRequest bannerRequest);

    /**
     * Update banner.
     *
     * @param bannerRequest the banner request
     * @param bannerId      the banner id
     */
    void updateBanner(BannerRequest bannerRequest, String bannerId);

    /**
     * Delete banner.
     *
     * @param id the id
     */
    void deleteBanner(String id);

    /**
     * Gets banner.
     *
     * @param id the id
     * @return the banner
     */
    BannerResponse getBanner(String id);

    /**
     * Hard delete banner.
     *
     * @param id the id
     */
    void hardDeleteBanner(String id);

    /**
     * Gets random banner.
     *
     * @return the random banner
     */
    List<BannerResponse> getRandomBanner();
}
