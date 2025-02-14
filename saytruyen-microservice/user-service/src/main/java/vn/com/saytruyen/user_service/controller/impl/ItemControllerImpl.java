package vn.com.saytruyen.user_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.user_service.controller.ItemController;
import vn.com.saytruyen.user_service.request.ItemRequest;
import vn.com.saytruyen.user_service.response.ItemResponse;
import vn.com.saytruyen.user_service.service.ItemService;

/**
 * The type Item controller.
 */
@Component
public class ItemControllerImpl implements ItemController {

    private final ItemService itemService;

    /**
     * Instantiates a new Item controller.
     *
     * @param itemService the item service
     */
    @Autowired
    public ItemControllerImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public ApiResponse<PageableResponse> getListItem(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>(itemService.getListItem(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> createItem(ItemRequest itemRequest) {
        itemService.createItem(itemRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateItem(ItemRequest itemRequest, Long id) {
        itemService.updateItem(itemRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteItem(Long id) {
        itemService.deleteItem(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<ItemResponse> getItem(Long id) {
        return new ApiResponse<>(itemService.getItem(id));
    }
}