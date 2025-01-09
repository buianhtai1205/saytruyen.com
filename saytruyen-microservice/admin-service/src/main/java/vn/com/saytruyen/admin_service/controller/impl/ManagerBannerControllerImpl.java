package vn.com.saytruyen.admin_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.admin_service.controller.ManagerBannerController;
import vn.com.saytruyen.admin_service.request.BannerRequest;
import vn.com.saytruyen.admin_service.service.BannerService;

/**
 * The type Manager banner controller.
 */
@Component
public class ManagerBannerControllerImpl implements ManagerBannerController {

    private final BannerService bannerService;

    /**
     * Instantiates a new Manager banner controller.
     *
     * @param bannerService the banner service
     */
    @Autowired
    public ManagerBannerControllerImpl(BannerService bannerService) {
        this.bannerService = bannerService;
    }


    @Override
    public ApiResponse<Object> getListBanner(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>(bannerService.getListBanner(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> createBanner(BannerRequest bannerRequest) {
        bannerService.createBanner(bannerRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateBanner(BannerRequest bannerRequest, String id) {
        bannerService.updateBanner(bannerRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteBanner(String id) {
        bannerService.deleteBanner(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Object> getBanner(String id) {
        return new ApiResponse<>(bannerService.getBanner(id));
    }
}
