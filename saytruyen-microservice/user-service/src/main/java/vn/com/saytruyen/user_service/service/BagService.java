package vn.com.saytruyen.user_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.user_service.request.BagRequest;
import vn.com.saytruyen.user_service.response.BagResponse;

/**
 * The interface Bag service.
 */
public interface BagService {

    /**
     * Gets list bag.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list bag
     */
    PageableResponse getListBag(Integer pageNumber, Integer pageSize);

    /**
     * Create bag.
     *
     * @param bagRequest the bag request
     */
    void createBag(BagRequest bagRequest);

    /**
     * Update bag.
     *
     * @param bagRequest the bag request
     * @param bagId      the bag id
     */
    void updateBag(BagRequest bagRequest, Long bagId);

    /**
     * Gets bag.
     *
     * @param id the id
     * @return the bag
     */
    BagResponse getBag(Long id);

    /**
     * Hard delete bag.
     *
     * @param id the id
     */
    void hardDeleteBag(Long id);
}