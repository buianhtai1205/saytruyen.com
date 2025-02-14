package vn.com.saytruyen.user_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.user_service.model.Item;
import vn.com.saytruyen.user_service.request.ItemRequest;
import vn.com.saytruyen.user_service.response.ItemResponse;

import java.util.List;

/**
 * The interface Item converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ItemConverter {

    /**
     * The constant INSTANCE.
     */
    ItemConverter INSTANCE = Mappers.getMapper(ItemConverter.class);

    /**
     * Item request to item item.
     *
     * @param itemRequest the item request
     * @return the item
     */
    Item itemRequestToItem(ItemRequest itemRequest);

    /**
     * Item to item response item response.
     *
     * @param item the item
     * @return the item response
     */
    ItemResponse itemToItemResponse(Item item);

    /**
     * Lst item to lst item response list.
     *
     * @param lstItem the lst item
     * @return the list
     */
    List<ItemResponse> lstItemToLstItemResponse(List<Item> lstItem);
}