package vn.com.saytruyen.user_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.user_service.request.ItemRequest;
import vn.com.saytruyen.user_service.response.ItemResponse;

/**
 * The interface Item service.
 */
public interface ItemService {

    /**
     * Gets list item.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list item
     */
    PageableResponse getListItem(Integer pageNumber, Integer pageSize);

    /**
     * Create item.
     *
     * @param itemRequest the item request
     */
    void createItem(ItemRequest itemRequest);

    /**
     * Update item.
     *
     * @param itemRequest the item request
     * @param itemId      the item id
     */
    void updateItem(ItemRequest itemRequest, Long itemId);

    /**
     * Delete item.
     *
     * @param id the id
     */
    void deleteItem(Long id);

    /**
     * Gets item.
     *
     * @param id the id
     * @return the item
     */
    ItemResponse getItem(Long id);

    /**
     * Hard delete item.
     *
     * @param id the id
     */
    void hardDeleteItem(Long id);
}