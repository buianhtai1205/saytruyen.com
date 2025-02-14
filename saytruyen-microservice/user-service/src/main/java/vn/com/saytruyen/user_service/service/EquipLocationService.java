package vn.com.saytruyen.user_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.user_service.request.EquipLocationRequest;
import vn.com.saytruyen.user_service.response.EquipLocationResponse;

/**
 * The interface Equip location service.
 */
public interface EquipLocationService {

    /**
     * Gets list equip location.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list equip location
     */
    PageableResponse getListEquipLocation(Integer pageNumber, Integer pageSize);

    /**
     * Create equip location.
     *
     * @param equipLocationRequest the equip location request
     */
    void createEquipLocation(EquipLocationRequest equipLocationRequest);

    /**
     * Update equip location.
     *
     * @param equipLocationRequest the equip location request
     * @param equipLocationId      the equip location id
     */
    void updateEquipLocation(EquipLocationRequest equipLocationRequest, Long equipLocationId);

    /**
     * Gets equip location.
     *
     * @param id the id
     * @return the equip location
     */
    EquipLocationResponse getEquipLocation(Long id);

    /**
     * Hard delete equip location.
     *
     * @param id the id
     */
    void hardDeleteEquipLocation(Long id);
}