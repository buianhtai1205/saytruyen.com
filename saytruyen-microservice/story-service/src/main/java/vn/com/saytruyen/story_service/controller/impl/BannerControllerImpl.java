package vn.com.saytruyen.story_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.story_service.controller.BannerController;
import vn.com.saytruyen.story_service.request.BannerRequest;
import vn.com.saytruyen.story_service.response.BannerResponse;
import vn.com.saytruyen.story_service.service.BannerService;

import java.util.List;

/**
 * The type Banner controller.
 */
@Component
public class BannerControllerImpl implements BannerController {

    @Autowired
    private BannerService bannerService;


    @Override
    public ApiResponse<PageableResponse> getListBanner(Integer pageNumber, Integer pageSize) {
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
    public ApiResponse<BannerResponse> getBanner(String id) {
        return new ApiResponse<>(bannerService.getBanner(id));
    }

    @Override
    public ApiResponse<List<BannerResponse>> getRandomBanner() {
        return new ApiResponse<>(bannerService.getRandomBanner());
    }
}
