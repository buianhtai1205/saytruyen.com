package vn.com.saytruyen.user_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.constant.CommonConstants;
import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.saytruyen.user_service.constant.ServiceConst;
import vn.com.saytruyen.user_service.converter.BagConverter;
import vn.com.saytruyen.user_service.model.Bag;
import vn.com.saytruyen.user_service.repository.BagRepository;
import vn.com.saytruyen.user_service.request.BagRequest;
import vn.com.saytruyen.user_service.response.BagResponse;
import vn.com.saytruyen.user_service.service.BagService;

import java.util.Objects;
import java.util.Optional;

@Service
public class BagServiceImpl implements BagService {

    @Autowired
    private BagRepository bagRepository;

    @Override
    public PageableResponse getListBag(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? CommonConstants.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? CommonConstants.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Bag> lstBag = bagRepository.findAll(pageable);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lstBag.getTotalPages())
                .totalElements(lstBag.getTotalElements())
                .data(BagConverter.INSTANCE.lstBagToLstBagResponse(lstBag.getContent()))
                .build();
    }

    @Override
    @Transactional
    public void createBag(BagRequest bagRequest) {
        Bag bag = BagConverter.INSTANCE.bagRequestToBag(bagRequest);
        bag.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        bag.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        bag.setDeleted(Boolean.FALSE);
        bagRepository.save(bag);
    }

    @Override
    @Transactional
    public void updateBag(BagRequest bagRequest, Long id) {
        Optional<Bag> currentBag = bagRepository.findById(id);
        if (currentBag.isPresent() && Objects.nonNull(bagRequest)) {
            Bag bagToUpdate = currentBag.get();
            ReflectionUtils.copyNonNullFields(bagRequest, bagToUpdate);
            bagToUpdate.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
            bagRepository.save(bagToUpdate);
        }
    }

    @Override
    @Transactional
    public void deleteBag(Long id) {
        Optional<Bag> currentBag = bagRepository.findById(id);
        if (currentBag.isPresent()) {
            Bag bagToDelete = currentBag.get();
            bagToDelete.setDeleted(Boolean.TRUE);
            bagToDelete.setDeletedAt(DateTimeUtils.getCurrentDateTime());
            bagRepository.save(bagToDelete);
        }
    }

    @Override
    public BagResponse getBag(Long id) {
        return BagConverter.INSTANCE.bagToBagResponse(getExistingBag(id));
    }

    @Override
    @Transactional
    public void hardDeleteBag(Long id) {
        Bag bag = getExistingBag(id);
        bagRepository.delete(bag);
    }

    public Bag getExistingBag(Long bagId) {
        Optional<Bag> existBag = bagRepository.findById(bagId);
        if (existBag.isPresent()) {
            return existBag.get();
        } else throw new ResourceNotFoundException(ServiceConst.BAG, bagId);
    }
}