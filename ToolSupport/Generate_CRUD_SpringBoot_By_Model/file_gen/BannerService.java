package com.example.demo.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import com.example.demo.request.BannerRequest;
import com.example.demo.response.BannerResponse;

public interface BannerService {

    PageableResponse getListBanner(Integer pageNumber, Integer pageSize);

    void createBanner(BannerRequest bannerRequest);

    void updateBanner(BannerRequest bannerRequest, String bannerId);

    void deleteBanner(String id);

    BannerResponse getBanner(String id);

    void hardDeleteBanner(String id);
}