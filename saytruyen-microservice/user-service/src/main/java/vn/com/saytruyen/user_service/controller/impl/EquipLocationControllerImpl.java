package vn.com.saytruyen.user_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.user_service.controller.EquipLocationController;
import vn.com.saytruyen.user_service.request.EquipLocationRequest;
import vn.com.saytruyen.user_service.response.EquipLocationResponse;
import vn.com.saytruyen.user_service.service.EquipLocationService;

/**
 * The type Equip location controller.
 */
@Component
public class EquipLocationControllerImpl implements EquipLocationController {

    private final EquipLocationService equipLocationService;

    /**
     * Instantiates a new Equip location controller.
     *
     * @param equipLocationService the equip location service
     */
    @Autowired
    public EquipLocationControllerImpl(EquipLocationService equipLocationService) {
        this.equipLocationService = equipLocationService;
    }

    @Override
    public ApiResponse<PageableResponse> getListEquipLocation(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>(equipLocationService.getListEquipLocation(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> createEquipLocation(EquipLocationRequest equipLocationRequest) {
        equipLocationService.createEquipLocation(equipLocationRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateEquipLocation(EquipLocationRequest equipLocationRequest, Long id) {
        equipLocationService.updateEquipLocation(equipLocationRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteEquipLocation(Long id) {
        equipLocationService.hardDeleteEquipLocation(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<EquipLocationResponse> getEquipLocation(Long id) {
        return new ApiResponse<>(equipLocationService.getEquipLocation(id));
    }
}