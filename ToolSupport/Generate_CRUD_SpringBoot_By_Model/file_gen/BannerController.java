package com.example.demo.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.request.BannerRequest;
import com.example.demo.response.BannerResponse;

@RestController
@RequestMapping("/api/banner")
public interface BannerController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PageableResponse> getListBanner(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createBanner(@RequestBody BannerRequest bannerRequest);

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateBanner(@RequestBody BannerRequest bannerRequest,
                                                @PathVariable String id);

    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteBanner(@PathVariable String id);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<BannerResponse> getBanner(@PathVariable String id);
}