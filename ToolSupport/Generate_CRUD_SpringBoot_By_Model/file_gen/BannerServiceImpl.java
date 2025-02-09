package com.example.demo.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.constant.ServiceConst;
import com.example.demo.converter.BannerConverter;
import com.example.demo.model.Banner;
import com.example.demo.repository.BannerRepository;
import com.example.demo.request.BannerRequest;
import com.example.demo.response.BannerResponse;
import com.example.demo.service.BannerService;

import java.util.Objects;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public PageableResponse getListBanner(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? StoryServiceConst.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? StoryServiceConst.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Banner> lstBanner = bannerRepository.findAll(pageable);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lstBanner.getTotalPages())
                .totalElements(lstBanner.getTotalElements())
                .data(BannerConverter.INSTANCE.lstBannerToLstBannerResponse(lstBanner.getContent()))
                .build();
    }

    @Override
    @Transactional
    public void createBanner(BannerRequest bannerRequest) {
        Banner banner = BannerConverter.INSTANCE.bannerRequestToBanner(bannerRequest);
        banner.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        banner.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        banner.setDeleted(Boolean.FALSE);
        bannerRepository.save(banner);
    }

    @Override
    @Transactional
    public void updateBanner(BannerRequest bannerRequest, String id) {
        Optional<Banner> currentBanner = bannerRepository.findById(id);
        if (currentBanner.isPresent() && Objects.nonNull(bannerRequest)) {
            Banner bannerToUpdate = currentBanner.get();
            ReflectionUtils.copyNonNullFields(bannerRequest, bannerToUpdate);
            bannerToUpdate.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
            bannerRepository.save(bannerToUpdate);
        }
    }

    @Override
    @Transactional
    public void deleteBanner(String id) {
        Optional<Banner> currentBanner = bannerRepository.findById(id);
        if (currentBanner.isPresent()) {
            Banner bannerToDelete = currentBanner.get();
            bannerToDelete.setDeleted(Boolean.TRUE);
            bannerToDelete.setDeletedAt(DateTimeUtils.getCurrentDateTime());
            bannerRepository.save(bannerToDelete);
        }
    }

    @Override
    public BannerResponse getBanner(String id) {
        return BannerConverter.INSTANCE.bannerToBannerResponse(getExistingBanner(id));
    }

    @Override
    @Transactional
    public void hardDeleteBanner(String id) {
        Banner banner = getExistingBanner(id);
        bannerRepository.delete(banner);
    }

    public Banner getExistingBanner(String bannerId) {
        Optional<Banner> existBanner = bannerRepository.findById(bannerId);
        if (existBanner.isPresent()) {
            return existBanner.get();
        } else throw new ResourceNotFoundException(ServiceConst.BANNER, bannerId);
    }
}