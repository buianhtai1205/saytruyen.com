package com.example.demo.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.controller.BannerController;
import com.example.demo.request.BannerRequest;
import com.example.demo.response.BannerResponse;
import com.example.demo.service.BannerService;

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
        BannerService.updateBanner(bannerRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteBanner(String id) {
        BannerService.deleteBanner(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<BannerResponse> getBanner(String id) {
        return new ApiResponse<>(bannerService.getBanner(id));
    }
}