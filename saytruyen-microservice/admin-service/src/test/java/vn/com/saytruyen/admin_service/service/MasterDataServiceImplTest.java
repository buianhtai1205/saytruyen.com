package vn.com.saytruyen.admin_service.service;

import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vn.com.saytruyen.admin_service.model.MCategory;
import vn.com.saytruyen.admin_service.model.MDataConfig;
import vn.com.saytruyen.admin_service.repository.MCategoryRepository;
import vn.com.saytruyen.admin_service.repository.MDataConfigRepository;
import vn.com.saytruyen.admin_service.request.MasterDataCategoryRequest;
import vn.com.saytruyen.admin_service.request.MasterDataConfigRequest;
import vn.com.saytruyen.admin_service.response.MasterDataCategoryResponse;
import vn.com.saytruyen.admin_service.response.MasterDataConfigResponse;
import vn.com.saytruyen.admin_service.service.impl.MasterDataServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Master data service impl test.
 */
public class MasterDataServiceImplTest {

    @Mock
    private MDataConfigRepository mDataConfigRepository;

    @Mock
    private MCategoryRepository mCategoryRepository;

    @InjectMocks
    private MasterDataServiceImpl masterDataService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test get master data config.
     */
    @Test
    void testGetMasterDataConfig() {
        MDataConfig mDataConfig = new MDataConfig();
        when(mDataConfigRepository.findById(any(Long.class))).thenReturn(Optional.of(mDataConfig));

        MasterDataConfigResponse response = masterDataService.getMasterDataConfig(1L);

        assertNotNull(response);
        verify(mDataConfigRepository, times(1)).findById(1L);
    }

    /**
     * Test get master data config not found.
     */
    @Test
    void testGetMasterDataConfig_NotFound() {
        when(mDataConfigRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> masterDataService.getMasterDataConfig(1L));
    }

    /**
     * Test create master data config.
     */
    @Test
    void testCreateMasterDataConfig() {
        MasterDataConfigRequest request = new MasterDataConfigRequest();
        MDataConfig mDataConfig = new MDataConfig();
        when(mDataConfigRepository.save(any(MDataConfig.class))).thenReturn(mDataConfig);

        masterDataService.createMasterDataConfig(request);

        verify(mDataConfigRepository, times(1)).save(any(MDataConfig.class));
    }

    /**
     * Test update master data config.
     */
    @Test
    void testUpdateMasterDataConfig() {
        MasterDataConfigRequest request = new MasterDataConfigRequest();
        MDataConfig mDataConfig = new MDataConfig();
        when(mDataConfigRepository.findById(any(Long.class))).thenReturn(Optional.of(mDataConfig));

        masterDataService.updateMasterDataConfig(request, 1L);

        verify(mDataConfigRepository, times(1)).save(any(MDataConfig.class));
    }

    /**
     * Test delete master data config.
     */
    @Test
    void testDeleteMasterDataConfig() {
        MDataConfig mDataConfig = new MDataConfig();
        when(mDataConfigRepository.findById(any(Long.class))).thenReturn(Optional.of(mDataConfig));

        masterDataService.deleteMasterDataConfig(1L);

        assertTrue(mDataConfig.isDeleted());
        verify(mDataConfigRepository, times(1)).save(mDataConfig);
    }

    /**
     * Test get master data category.
     */
    @Test
    void testGetMasterDataCategory() {
        MCategory mCategory = new MCategory();
        when(mCategoryRepository.findById(any(Long.class))).thenReturn(Optional.of(mCategory));

        MasterDataCategoryResponse response = masterDataService.getMasterDataCategory(1L);

        assertNotNull(response);
        verify(mCategoryRepository, times(1)).findById(1L);
    }

    /**
     * Test get master data category not found.
     */
    @Test
    void testGetMasterDataCategory_NotFound() {
        when(mCategoryRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> masterDataService.getMasterDataCategory(1L));
    }

    /**
     * Test create master data category.
     */
    @Test
    void testCreateMasterDataCategory() {
        MasterDataCategoryRequest request = new MasterDataCategoryRequest();
        MCategory mCategory = new MCategory();
        when(mCategoryRepository.save(any(MCategory.class))).thenReturn(mCategory);

        masterDataService.createMasterDataCategory(request);

        verify(mCategoryRepository, times(1)).save(any(MCategory.class));
    }

    /**
     * Test update master data category.
     */
    @Test
    void testUpdateMasterDataCategory() {
        MasterDataCategoryRequest request = new MasterDataCategoryRequest();
        MCategory mCategory = new MCategory();
        when(mCategoryRepository.findById(any(Long.class))).thenReturn(Optional.of(mCategory));

        masterDataService.updateMasterDataCategory(request, 1L);

        verify(mCategoryRepository, times(1)).save(any(MCategory.class));
    }

    /**
     * Test delete master data category.
     */
    @Test
    void testDeleteMasterDataCategory() {
        MCategory mCategory = new MCategory();
        when(mCategoryRepository.findById(any(Long.class))).thenReturn(Optional.of(mCategory));

        masterDataService.deleteMasterDataCategory(1L);

        assertTrue(mCategory.isDeleted());
        verify(mCategoryRepository, times(1)).save(mCategory);
    }
}
