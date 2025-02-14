package vn.com.saytruyen.user_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.constant.CommonConstants;
import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.saytruyen.user_service.constant.UserServiceConst;
import vn.com.saytruyen.user_service.converter.EquipLocationConverter;
import vn.com.saytruyen.user_service.model.EquipLocation;
import vn.com.saytruyen.user_service.repository.EquipLocationRepository;
import vn.com.saytruyen.user_service.request.EquipLocationRequest;
import vn.com.saytruyen.user_service.response.EquipLocationResponse;
import vn.com.saytruyen.user_service.service.EquipLocationService;

import java.util.Objects;
import java.util.Optional;

/**
 * The type Equip location service.
 */
@Service
public class EquipLocationServiceImpl implements EquipLocationService {

    private final EquipLocationRepository equipLocationRepository;

    /**
     * Instantiates a new Equip location service.
     *
     * @param equipLocationRepository the equip location repository
     */
    @Autowired
    public EquipLocationServiceImpl(EquipLocationRepository equipLocationRepository) {
        this.equipLocationRepository = equipLocationRepository;
    }

    @Override
    public PageableResponse getListEquipLocation(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? CommonConstants.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? CommonConstants.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<EquipLocation> lstEquipLocation = equipLocationRepository.findAll(pageable);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lstEquipLocation.getTotalPages())
                .totalElements(lstEquipLocation.getTotalElements())
                .data(EquipLocationConverter.INSTANCE.lstEquipLocationToLstEquipLocationResponse(lstEquipLocation.getContent()))
                .build();
    }

    @Override
    @Transactional
    public void createEquipLocation(EquipLocationRequest equipLocationRequest) {
        EquipLocation equipLocation = EquipLocationConverter.INSTANCE.equipLocationRequestToEquipLocation(equipLocationRequest);
        equipLocationRepository.save(equipLocation);
    }

    @Override
    @Transactional
    public void updateEquipLocation(EquipLocationRequest equipLocationRequest, Long id) {
        Optional<EquipLocation> currentEquipLocation = equipLocationRepository.findById(id);
        if (currentEquipLocation.isPresent() && Objects.nonNull(equipLocationRequest)) {
            EquipLocation equipLocationToUpdate = currentEquipLocation.get();
            ReflectionUtils.copyNonNullFields(equipLocationRequest, equipLocationToUpdate);
            equipLocationRepository.save(equipLocationToUpdate);
        }
    }

    @Override
    public EquipLocationResponse getEquipLocation(Long id) {
        return EquipLocationConverter.INSTANCE.equipLocationToEquipLocationResponse(getExistingEquipLocation(id));
    }

    @Override
    @Transactional
    public void hardDeleteEquipLocation(Long id) {
        EquipLocation equipLocation = getExistingEquipLocation(id);
        equipLocationRepository.delete(equipLocation);
    }

    /**
     * Gets existing equip location.
     *
     * @param equipLocationId the equip location id
     * @return the existing equip location
     */
    public EquipLocation getExistingEquipLocation(Long equipLocationId) {
        Optional<EquipLocation> existEquipLocation = equipLocationRepository.findById(equipLocationId);
        if (existEquipLocation.isPresent()) {
            return existEquipLocation.get();
        } else throw new ResourceNotFoundException(UserServiceConst.EQUIP_LOCATION, equipLocationId);
    }
}