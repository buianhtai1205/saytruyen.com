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
import vn.com.saytruyen.user_service.constant.UserServiceConst;
import vn.com.saytruyen.user_service.converter.ItemConverter;
import vn.com.saytruyen.user_service.model.Item;
import vn.com.saytruyen.user_service.repository.ItemRepository;
import vn.com.saytruyen.user_service.request.ItemRequest;
import vn.com.saytruyen.user_service.response.ItemResponse;
import vn.com.saytruyen.user_service.service.ItemService;

import java.util.Objects;
import java.util.Optional;

/**
 * The type Item service.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    /**
     * Instantiates a new Item service.
     *
     * @param itemRepository the item repository
     */
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public PageableResponse getListItem(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? CommonConstants.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? CommonConstants.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Item> lstItem = itemRepository.findAll(pageable);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lstItem.getTotalPages())
                .totalElements(lstItem.getTotalElements())
                .data(ItemConverter.INSTANCE.lstItemToLstItemResponse(lstItem.getContent()))
                .build();
    }

    @Override
    @Transactional
    public void createItem(ItemRequest itemRequest) {
        Item item = ItemConverter.INSTANCE.itemRequestToItem(itemRequest);
        item.setDeleted(Boolean.FALSE);
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void updateItem(ItemRequest itemRequest, Long id) {
        Optional<Item> currentItem = itemRepository.findById(id);
        if (currentItem.isPresent() && Objects.nonNull(itemRequest)) {
            Item itemToUpdate = currentItem.get();
            ReflectionUtils.copyNonNullFields(itemRequest, itemToUpdate);
            itemRepository.save(itemToUpdate);
        }
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        Optional<Item> currentItem = itemRepository.findById(id);
        if (currentItem.isPresent()) {
            Item itemToDelete = currentItem.get();
            itemToDelete.setDeleted(Boolean.TRUE);
            itemToDelete.setDeletedAt(DateTimeUtils.getCurrentDateTime());
            itemRepository.save(itemToDelete);
        }
    }

    @Override
    public ItemResponse getItem(Long id) {
        return ItemConverter.INSTANCE.itemToItemResponse(getExistingItem(id));
    }

    @Override
    @Transactional
    public void hardDeleteItem(Long id) {
        Item item = getExistingItem(id);
        itemRepository.delete(item);
    }

    /**
     * Gets existing item.
     *
     * @param itemId the item id
     * @return the existing item
     */
    public Item getExistingItem(Long itemId) {
        Optional<Item> existItem = itemRepository.findById(itemId);
        if (existItem.isPresent()) {
            return existItem.get();
        } else throw new ResourceNotFoundException(UserServiceConst.ITEM, itemId);
    }
}