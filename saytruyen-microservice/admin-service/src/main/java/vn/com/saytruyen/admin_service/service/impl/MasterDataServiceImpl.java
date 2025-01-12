package vn.com.saytruyen.admin_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.saytruyen.admin_service.constant.AdminServiceConst;
import vn.com.saytruyen.admin_service.converter.MasterDataConverter;
import vn.com.saytruyen.admin_service.model.MCategory;
import vn.com.saytruyen.admin_service.model.MDataConfig;
import vn.com.saytruyen.admin_service.repository.MCategoryRepository;
import vn.com.saytruyen.admin_service.repository.MDataConfigRepository;
import vn.com.saytruyen.admin_service.request.MasterDataCategoryRequest;
import vn.com.saytruyen.admin_service.request.MasterDataConfigRequest;
import vn.com.saytruyen.admin_service.response.MasterDataCategoryResponse;
import vn.com.saytruyen.admin_service.response.MasterDataConfigResponse;
import vn.com.saytruyen.admin_service.service.MasterDataService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Master data service.
 */
@Service
public class MasterDataServiceImpl implements MasterDataService {

    private final MDataConfigRepository mDataConfigRepository;

    private final MCategoryRepository mCategoryRepository;

    /**
     * Instantiates a new Master data service.
     *
     * @param mDataConfigRepository the m data config repository
     * @param mCategoryRepository   the m category repository
     */
    @Autowired
    public MasterDataServiceImpl(
            MDataConfigRepository mDataConfigRepository,
            MCategoryRepository mCategoryRepository) {
        this.mDataConfigRepository = mDataConfigRepository;
        this.mCategoryRepository = mCategoryRepository;
    }

    @Override
    public List<MasterDataConfigResponse> getAllMasterDataConfig() {
        return MasterDataConverter.INSTANCE.lstMDataConfigToLstMDataConfigResponse(mDataConfigRepository.findAll());
    }

    @Override
    public MasterDataConfigResponse getMasterDataConfig(Long masterDataConfigId) {
        return MasterDataConverter.INSTANCE.mDataConfigToMDataConfigResponse(getExistMDataConfig(masterDataConfigId));
    }

    @Override
    @Transactional
    public void createMasterDataConfig(MasterDataConfigRequest masterDataConfigRequest) {
        MDataConfig mDataConfig = MasterDataConverter.INSTANCE.masterDataConfigRequestToMDataConfig(masterDataConfigRequest);
        mDataConfig.setDeleted(Boolean.FALSE);
        mDataConfigRepository.save(mDataConfig);
    }

    @Override
    @Transactional
    public void updateMasterDataConfig(MasterDataConfigRequest masterDataConfigRequest, Long masterDataConfigId) {
        MDataConfig existMDataConfig = getExistMDataConfig(masterDataConfigId);
        if (Objects.nonNull(masterDataConfigRequest) && Objects.nonNull(existMDataConfig)) {
            ReflectionUtils.copyNonNullFields(masterDataConfigRequest, existMDataConfig);
            mDataConfigRepository.save(existMDataConfig);
        }
    }

    @Override
    @Transactional
    public void deleteMasterDataConfig(Long masterDataConfigId) {
        MDataConfig existMDataConfig = getExistMDataConfig(masterDataConfigId);
        existMDataConfig.setDeleted(Boolean.TRUE);
        mDataConfigRepository.save(existMDataConfig);
    }

    @Override
    @Transactional
    public void hardDeleteMasterDataConfig(Long masterDataConfigId) {
        MDataConfig existMDataConfig = getExistMDataConfig(masterDataConfigId);
        mDataConfigRepository.delete(existMDataConfig);
    }

    @Override
    public List<MasterDataCategoryResponse> getAllMasterDataCategory() {
        return MasterDataConverter.INSTANCE.lstMCategoryToLstMCategoryResponse(mCategoryRepository.findAll());
    }

    @Override
    public MasterDataCategoryResponse getMasterDataCategory(Long masterDataCategoryId) {
        return MasterDataConverter.INSTANCE.mCategoryToMCategoryResponse(getExistMCategory(masterDataCategoryId));
    }

    @Override
    @Transactional
    public void createMasterDataCategory(MasterDataCategoryRequest masterDataCategoryRequest) {
        MCategory mCategory = MasterDataConverter.INSTANCE.masterDataCategoryRequestToMCategory(masterDataCategoryRequest);
        mCategory.setDeleted(Boolean.FALSE);
        mCategoryRepository.save(mCategory);
    }

    @Override
    @Transactional
    public void updateMasterDataCategory(MasterDataCategoryRequest masterDataCategoryRequest, Long masterDataCategoryId) {
        MCategory existMCategory = getExistMCategory(masterDataCategoryId);
        if (Objects.nonNull(masterDataCategoryRequest) && Objects.nonNull(existMCategory)) {
            ReflectionUtils.copyNonNullFields(masterDataCategoryRequest, existMCategory);
            mCategoryRepository.save(existMCategory);
        }
    }

    @Override
    @Transactional
    public void deleteMasterDataCategory(Long masterDataCategoryId) {
        MCategory existMCategory = getExistMCategory(masterDataCategoryId);
        existMCategory.setDeleted(Boolean.TRUE);
        mCategoryRepository.save(existMCategory);
    }

    @Override
    @Transactional
    public void hardDeleteMasterDataCategory(Long masterDataCategoryId) {
        MCategory existMCategory = getExistMCategory(masterDataCategoryId);
        mCategoryRepository.delete(existMCategory);
    }

    private MDataConfig getExistMDataConfig(Long masterDataConfigId) {
        Optional<MDataConfig> existMDataConfig = mDataConfigRepository.findById(masterDataConfigId);
        if (existMDataConfig.isPresent()) {
            return existMDataConfig.get();
        } else throw new ResourceNotFoundException(
                AdminServiceConst.M_DATA_CONFIG_EN,
                String.valueOf(masterDataConfigId)
        );
    }

    private MCategory getExistMCategory(Long masterDataCategoryId) {
        Optional<MCategory> existMCategory = mCategoryRepository.findById(masterDataCategoryId);
        if (existMCategory.isPresent()) {
            return existMCategory.get();
        } else throw new ResourceNotFoundException(
                AdminServiceConst.M_DATA_CATEGORY_EN,
                String.valueOf(masterDataCategoryId)
        );
    }
}
