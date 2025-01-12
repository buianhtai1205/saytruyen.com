package vn.com.saytruyen.admin_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.admin_service.model.MCategory;
import vn.com.saytruyen.admin_service.model.MDataConfig;
import vn.com.saytruyen.admin_service.request.MasterDataCategoryRequest;
import vn.com.saytruyen.admin_service.request.MasterDataConfigRequest;
import vn.com.saytruyen.admin_service.response.MasterDataCategoryResponse;
import vn.com.saytruyen.admin_service.response.MasterDataConfigResponse;

import java.util.List;

/**
 * The interface Master data converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface MasterDataConverter {

    /**
     * The constant INSTANCE.
     */
    MasterDataConverter INSTANCE = Mappers.getMapper(MasterDataConverter.class);

    /**
     * Lst m data config to lst m data config response list.
     *
     * @param lstMDataConfig the lst m data config
     * @return the list
     */
    List<MasterDataConfigResponse> lstMDataConfigToLstMDataConfigResponse(List<MDataConfig> lstMDataConfig);

    /**
     * M data config to m data config response master data config response.
     *
     * @param existMDataConfig the exist m data config
     * @return the master data config response
     */
    MasterDataConfigResponse mDataConfigToMDataConfigResponse(MDataConfig existMDataConfig);

    /**
     * Master data config request to m data config m data config.
     *
     * @param masterDataConfigRequest the master data config request
     * @return the m data config
     */
    MDataConfig masterDataConfigRequestToMDataConfig(MasterDataConfigRequest masterDataConfigRequest);

    /**
     * Lst m category to lst m category response list.
     *
     * @param lstMCategory the lst m category
     * @return the list
     */
    List<MasterDataCategoryResponse> lstMCategoryToLstMCategoryResponse(List<MCategory> lstMCategory);

    /**
     * M category to m category response master data category response.
     *
     * @param existMCategory the exist m category
     * @return the master data category response
     */
    MasterDataCategoryResponse mCategoryToMCategoryResponse(MCategory existMCategory);

    /**
     * Master data category request to m category m category.
     *
     * @param masterDataCategoryRequest the master data category request
     * @return the m category
     */
    MCategory masterDataCategoryRequestToMCategory(MasterDataCategoryRequest masterDataCategoryRequest);
}
