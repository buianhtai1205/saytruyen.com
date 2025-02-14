package vn.com.saytruyen.user_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.user_service.model.Bag;
import vn.com.saytruyen.user_service.request.BagRequest;
import vn.com.saytruyen.user_service.response.BagResponse;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BagConverter {

    BagConverter INSTANCE = Mappers.getMapper(BagConverter.class);

    Bag bagRequestToBag(BagRequest bagRequest);

    BagResponse bagToBagResponse(Bag bag);

    List<BagResponse> lstBagToLstBagResponse(List<Bag> lstBag);
}