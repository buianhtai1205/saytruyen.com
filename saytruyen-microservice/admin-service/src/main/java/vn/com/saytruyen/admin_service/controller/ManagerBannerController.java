package vn.com.saytruyen.admin_service.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
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
import vn.com.saytruyen.admin_service.request.BannerRequest;

/**
 * The interface Banner controller.
 */
@RestController
@RequestMapping("/api/admin-service/banner")
public interface ManagerBannerController {


    /**
     * Gets list banner.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list banner
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Object> getListBanner(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize);


    /**
     * Create banner api response.
     *
     * @param bannerRequest the banner request
     * @return the api response
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createBanner(@RequestBody BannerRequest bannerRequest);


    /**
     * Update banner api response.
     *
     * @param bannerRequest the banner request
     * @param id            the id
     * @return the api response
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateBanner(@RequestBody BannerRequest bannerRequest, @PathVariable String id);


    /**
     * Delete banner api response.
     *
     * @param id the id
     * @return the api response
     */
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteBanner(@PathVariable String id);


    /**
     * Gets banner.
     *
     * @param id the id
     * @return the banner
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Object> getBanner(@PathVariable String id);
}
