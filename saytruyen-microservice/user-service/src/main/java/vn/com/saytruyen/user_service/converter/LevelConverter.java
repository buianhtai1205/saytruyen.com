package vn.com.saytruyen.user_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.user_service.model.Level;
import vn.com.saytruyen.user_service.request.LevelRequest;
import vn.com.saytruyen.user_service.response.LevelResponse;

import java.util.List;

/**
 * The interface Level converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface LevelConverter {

    /**
     * The constant INSTANCE.
     */
    LevelConverter INSTANCE = Mappers.getMapper(LevelConverter.class);

    /**
     * Level request to level level.
     *
     * @param levelRequest the level request
     * @return the level
     */
    Level levelRequestToLevel(LevelRequest levelRequest);

    /**
     * Level to level response level response.
     *
     * @param level the level
     * @return the level response
     */
    LevelResponse levelToLevelResponse(Level level);

    /**
     * Lst level to lst level response list.
     *
     * @param lstLevel the lst level
     * @return the list
     */
    List<LevelResponse> lstLevelToLstLevelResponse(List<Level> lstLevel);
}