package vn.com.saytruyen.admin_service.service;

import vn.com.saytruyen.admin_service.request.MasterDataCategoryRequest;
import vn.com.saytruyen.admin_service.request.MasterDataConfigRequest;
import vn.com.saytruyen.admin_service.response.MasterDataCategoryResponse;
import vn.com.saytruyen.admin_service.response.MasterDataConfigResponse;

import java.util.List;

/**
 * The interface Master data service.
 */
public interface MasterDataService {

    /**
     * Gets all master data config.
     *
     * @return the all master data config
     */
    List<MasterDataConfigResponse> getAllMasterDataConfig();

    /**
     * Gets master data config.
     *
     * @param masterDataConfigId the master data config id
     * @return the master data config
     */
    MasterDataConfigResponse getMasterDataConfig(Long masterDataConfigId);

    /**
     * Create master data config.
     *
     * @param masterDataConfigRequest the master data config request
     */
    void createMasterDataConfig(MasterDataConfigRequest masterDataConfigRequest);

    /**
     * Update master data config.
     *
     * @param masterDataConfigRequest the master data config request
     * @param masterDataConfigId      the master data config id
     */
    void updateMasterDataConfig(MasterDataConfigRequest masterDataConfigRequest, Long masterDataConfigId);

    /**
     * Delete master data config.
     *
     * @param masterDataConfigId the master data config id
     */
    void deleteMasterDataConfig(Long masterDataConfigId);

    /**
     * Hard delete master data config.
     *
     * @param masterDataConfigId the master data config id
     */
    void hardDeleteMasterDataConfig(Long masterDataConfigId);

    /**
     * Gets all master data category.
     *
     * @return the all master data category
     */
    List<MasterDataCategoryResponse> getAllMasterDataCategory();

    /**
     * Gets master data category.
     *
     * @param masterDataCategoryId the master data category id
     * @return the master data category
     */
    MasterDataCategoryResponse getMasterDataCategory(Long masterDataCategoryId);

    /**
     * Create master data category.
     *
     * @param masterDataCategoryRequest the master data category request
     */
    void createMasterDataCategory(MasterDataCategoryRequest masterDataCategoryRequest);

    /**
     * Update master data category.
     *
     * @param masterDataCategoryRequest the master data category request
     * @param masterDataCategoryId      the master data category id
     */
    void updateMasterDataCategory(MasterDataCategoryRequest masterDataCategoryRequest, Long masterDataCategoryId);

    /**
     * Delete master data category.
     *
     * @param masterDataCategoryId the master data category id
     */
    void deleteMasterDataCategory(Long masterDataCategoryId);

    /**
     * Hard delete master data category.
     *
     * @param masterDataCategoryId the master data category id
     */
    void hardDeleteMasterDataCategory(Long masterDataCategoryId);
}
