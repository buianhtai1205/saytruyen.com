package vn.com.saytruyen.user_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.user_service.model.Bag;
import vn.com.saytruyen.user_service.request.BagRequest;
import vn.com.saytruyen.user_service.response.BagResponse;

import java.util.List;

/**
 * The interface Bag converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BagConverter {

    /**
     * The constant INSTANCE.
     */
    BagConverter INSTANCE = Mappers.getMapper(BagConverter.class);

    /**
     * Bag request to bag bag.
     *
     * @param bagRequest the bag request
     * @return the bag
     */
    Bag bagRequestToBag(BagRequest bagRequest);

    /**
     * Bag to bag response bag response.
     *
     * @param bag the bag
     * @return the bag response
     */
    BagResponse bagToBagResponse(Bag bag);

    /**
     * Lst bag to lst bag response list.
     *
     * @param lstBag the lst bag
     * @return the list
     */
    List<BagResponse> lstBagToLstBagResponse(List<Bag> lstBag);
}