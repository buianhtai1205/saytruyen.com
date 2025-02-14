package vn.com.saytruyen.user_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.user_service.controller.BagController;
import vn.com.saytruyen.user_service.request.BagRequest;
import vn.com.saytruyen.user_service.response.BagResponse;
import vn.com.saytruyen.user_service.service.BagService;

/**
 * The type Bag controller.
 */
@Component
public class BagControllerImpl implements BagController {

    private final BagService bagService;

    /**
     * Instantiates a new Bag controller.
     *
     * @param bagService the bag service
     */
    @Autowired
    public BagControllerImpl(BagService bagService) {
        this.bagService = bagService;
    }

    @Override
    public ApiResponse<PageableResponse> getListBag(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>(bagService.getListBag(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> createBag(BagRequest bagRequest) {
        bagService.createBag(bagRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateBag(BagRequest bagRequest, Long id) {
        bagService.updateBag(bagRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteBag(Long id) {
        bagService.hardDeleteBag(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<BagResponse> getBag(Long id) {
        return new ApiResponse<>(bagService.getBag(id));
    }
}