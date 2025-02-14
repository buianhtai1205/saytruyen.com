package vn.com.saytruyen.user_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.user_service.model.EquipLocation;
import vn.com.saytruyen.user_service.request.EquipLocationRequest;
import vn.com.saytruyen.user_service.response.EquipLocationResponse;

import java.util.List;

/**
 * The interface Equip location converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface EquipLocationConverter {

    /**
     * The constant INSTANCE.
     */
    EquipLocationConverter INSTANCE = Mappers.getMapper(EquipLocationConverter.class);

    /**
     * Equip location request to equip location equip location.
     *
     * @param equipLocationRequest the equip location request
     * @return the equip location
     */
    EquipLocation equipLocationRequestToEquipLocation(EquipLocationRequest equipLocationRequest);

    /**
     * Equip location to equip location response equip location response.
     *
     * @param equipLocation the equip location
     * @return the equip location response
     */
    EquipLocationResponse equipLocationToEquipLocationResponse(EquipLocation equipLocation);

    /**
     * Lst equip location to lst equip location response list.
     *
     * @param lstEquipLocation the lst equip location
     * @return the list
     */
    List<EquipLocationResponse> lstEquipLocationToLstEquipLocationResponse(List<EquipLocation> lstEquipLocation);
}