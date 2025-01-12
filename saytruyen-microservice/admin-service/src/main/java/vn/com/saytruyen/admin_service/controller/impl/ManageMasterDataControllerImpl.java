package vn.com.saytruyen.admin_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.admin_service.controller.ManageMasterDataController;
import vn.com.saytruyen.admin_service.request.MasterDataCategoryRequest;
import vn.com.saytruyen.admin_service.request.MasterDataConfigRequest;
import vn.com.saytruyen.admin_service.response.MasterDataCategoryResponse;
import vn.com.saytruyen.admin_service.response.MasterDataConfigResponse;
import vn.com.saytruyen.admin_service.service.MasterDataService;

import java.util.List;

/**
 * The type Manage master data controller.
 */
@Component
public class ManageMasterDataControllerImpl implements ManageMasterDataController {

    private final MasterDataService masterDataService;

    /**
     * Instantiates a new Manage master data controller.
     *
     * @param masterDataService the master data service
     */
    @Autowired
    public ManageMasterDataControllerImpl(MasterDataService masterDataService) {
        this.masterDataService = masterDataService;
    }

    @Override
    public ApiResponse<List<MasterDataConfigResponse>> getAllMasterDataConfig() {
        return new ApiResponse<>(masterDataService.getAllMasterDataConfig());
    }

    @Override
    public ApiResponse<MasterDataConfigResponse> getMasterDataConfig(Long masterDataConfigId) {
        return new ApiResponse<>(masterDataService.getMasterDataConfig(masterDataConfigId));
    }

    @Override
    public ApiResponse<Boolean> createMasterDataConfig(MasterDataConfigRequest masterDataConfigRequest) {
        masterDataService.createMasterDataConfig(masterDataConfigRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateMasterDataConfig(MasterDataConfigRequest masterDataConfigRequest, Long masterDataConfigId) {
        masterDataService.updateMasterDataConfig(masterDataConfigRequest, masterDataConfigId);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteMasterDataConfig(Long masterDataConfigId) {
        masterDataService.deleteMasterDataConfig(masterDataConfigId);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> hardDeleteMasterDataConfig(Long masterDataConfigId) {
        masterDataService.hardDeleteMasterDataConfig(masterDataConfigId);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<List<MasterDataCategoryResponse>> getAllMasterDataCategory() {
        return new ApiResponse<>(masterDataService.getAllMasterDataCategory());
    }

    @Override
    public ApiResponse<MasterDataCategoryResponse> getMasterDataCategory(Long masterDataCategoryId) {
        return new ApiResponse<>(masterDataService.getMasterDataCategory(masterDataCategoryId));
    }

    @Override
    public ApiResponse<Boolean> createMasterDataCategory(MasterDataCategoryRequest masterDataCategoryRequest) {
        masterDataService.createMasterDataCategory(masterDataCategoryRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateMasterDataCategory(MasterDataCategoryRequest masterDataCategoryRequest, Long masterDataCategoryId) {
        masterDataService.updateMasterDataCategory(masterDataCategoryRequest, masterDataCategoryId);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteMasterDataCategory(Long masterDataCategoryId) {
        masterDataService.deleteMasterDataCategory(masterDataCategoryId);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> hardDeleteMasterDataCategory(Long masterDataCategoryId) {
        masterDataService.hardDeleteMasterDataCategory(masterDataCategoryId);
        return new ApiResponse<>(Boolean.TRUE);
    }
}
