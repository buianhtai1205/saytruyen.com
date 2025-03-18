package vn.com.saytruyen.story_service.service.impl;

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
import vn.com.saytruyen.story_service.constant.StoryServiceConst;
import vn.com.saytruyen.story_service.converter.BannerConverter;
import vn.com.saytruyen.story_service.model.Banner;
import vn.com.saytruyen.story_service.repository.BannerRepository;
import vn.com.saytruyen.story_service.request.BannerRequest;
import vn.com.saytruyen.story_service.response.BannerResponse;
import vn.com.saytruyen.story_service.service.BannerService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Banner service.
 */
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

    @Override
    public List<BannerResponse> getRandomBanner() {
        List<Banner> lstBanner = bannerRepository.findAll();
        Collections.shuffle(lstBanner);
        List<Banner> randomBanners = lstBanner.stream().limit(10).collect(Collectors.toList());
        return BannerConverter.INSTANCE.lstBannerToLstBannerResponse(randomBanners);
    }

    /**
     * Gets existing banner.
     *
     * @param bannerId the banner id
     * @return the existing banner
     */
    public Banner getExistingBanner(String bannerId) {
        Optional<Banner> existBanner = bannerRepository.findById(bannerId);
        if (existBanner.isPresent()) {
            return existBanner.get();
        } else throw new ResourceNotFoundException(StoryServiceConst.BANNER_VN, bannerId);
    }
}
