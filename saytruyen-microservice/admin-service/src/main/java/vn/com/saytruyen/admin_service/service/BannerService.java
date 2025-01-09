package vn.com.saytruyen.admin_service.service;

import vn.com.saytruyen.admin_service.request.BannerRequest;

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
    Object getListBanner(Integer pageNumber, Integer pageSize);

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
     * @param id            the id
     */
    void updateBanner(BannerRequest bannerRequest, String id);

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
    Object getBanner(String id);
}
