package vn.com.saytruyen.admin_service.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.com.saytruyen.admin_service.request.MasterDataCategoryRequest;
import vn.com.saytruyen.admin_service.request.MasterDataConfigRequest;
import vn.com.saytruyen.admin_service.response.MasterDataCategoryResponse;
import vn.com.saytruyen.admin_service.response.MasterDataConfigResponse;

import java.util.List;

/**
 * The interface Manage master data controller.
 */
@RestController
@RequestMapping("/api/admin-service")
public interface ManageMasterDataController {

    /**
     * Gets all master data config.
     *
     * @return the all master data config
     */
    @GetMapping("/master-data-config")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<MasterDataConfigResponse>> getAllMasterDataConfig();

    /**
     * Gets master data config.
     *
     * @param masterDataConfigId the master data config id
     * @return the master data config
     */
    @GetMapping("/master-data-config/{masterDataConfigId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<MasterDataConfigResponse> getMasterDataConfig(@PathVariable Long masterDataConfigId);

    /**
     * Create master data config api response.
     *
     * @param masterDataConfigRequest the master data config request
     * @return the api response
     */
    @PostMapping("/master-data-config/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createMasterDataConfig(@RequestBody MasterDataConfigRequest masterDataConfigRequest);

    /**
     * Update master data config api response.
     *
     * @param masterDataConfigRequest the master data config request
     * @param masterDataConfigId      the master data config id
     * @return the api response
     */
    @PutMapping("/master-data-config/update/{masterDataConfigId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateMasterDataConfig(@RequestBody MasterDataConfigRequest masterDataConfigRequest, @PathVariable Long masterDataConfigId);

    /**
     * Delete master data config api response.
     *
     * @param masterDataConfigId the master data config id
     * @return the api response
     */
    @PutMapping("/master-data-config/delete/{masterDataConfigId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteMasterDataConfig(@PathVariable Long masterDataConfigId);

    /**
     * Hard delete master data config api response.
     *
     * @param masterDataConfigId the master data config id
     * @return the api response
     */
    @DeleteMapping("/master-data-config/hard-delete/{masterDataConfigId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> hardDeleteMasterDataConfig(@PathVariable Long masterDataConfigId);

    /**
     * Gets all master data category.
     *
     * @return the all master data category
     */
    @GetMapping("/master-data-category")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<MasterDataCategoryResponse>> getAllMasterDataCategory();

    /**
     * Gets master data category.
     *
     * @param masterDataCategoryId the master data category id
     * @return the master data category
     */
    @GetMapping("/master-data-category/{masterDataCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<MasterDataCategoryResponse> getMasterDataCategory(@PathVariable Long masterDataCategoryId);

    /**
     * Create master data category api response.
     *
     * @param masterDataCategoryRequest the master data category request
     * @return the api response
     */
    @PostMapping("/master-data-category/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createMasterDataCategory(@RequestBody MasterDataCategoryRequest masterDataCategoryRequest);

    /**
     * Update master data category api response.
     *
     * @param masterDataCategoryRequest the master data category request
     * @param masterDataCategoryId      the master data category id
     * @return the api response
     */
    @PutMapping("/master-data-category/update/{masterDataCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateMasterDataCategory(@RequestBody MasterDataCategoryRequest masterDataCategoryRequest, @PathVariable Long masterDataCategoryId);

    /**
     * Delete master data category api response.
     *
     * @param masterDataCategoryId the master data category id
     * @return the api response
     */
    @PutMapping("/master-data-category/delete/{masterDataCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteMasterDataCategory(@PathVariable Long masterDataCategoryId);

    /**
     * Hard delete master data category api response.
     *
     * @param masterDataCategoryId the master data category id
     * @return the api response
     */
    @DeleteMapping("/master-data-category/hard-delete/{masterDataCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> hardDeleteMasterDataCategory(@PathVariable Long masterDataCategoryId);
}
