package vn.com.saytruyen.user_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.user_service.request.BagRequest;
import vn.com.saytruyen.user_service.response.BagResponse;

public interface BagService {

    PageableResponse getListBag(Integer pageNumber, Integer pageSize);

    void createBag(BagRequest bagRequest);

    void updateBag(BagRequest bagRequest, Long bagId);

    void deleteBag(Long id);

    BagResponse getBag(Long id);

    void hardDeleteBag(Long id);
}