package vn.com.saytruyen.user_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.user_service.request.LevelRequest;
import vn.com.saytruyen.user_service.response.LevelResponse;

/**
 * The interface Level service.
 */
public interface LevelService {

    /**
     * Gets list level.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list level
     */
    PageableResponse getListLevel(Integer pageNumber, Integer pageSize);

    /**
     * Create level.
     *
     * @param levelRequest the level request
     */
    void createLevel(LevelRequest levelRequest);

    /**
     * Update level.
     *
     * @param levelRequest the level request
     * @param levelId      the level id
     */
    void updateLevel(LevelRequest levelRequest, Long levelId);

    /**
     * Gets level.
     *
     * @param id the id
     * @return the level
     */
    LevelResponse getLevel(Long id);

    /**
     * Hard delete level.
     *
     * @param id the id
     */
    void hardDeleteLevel(Long id);
}