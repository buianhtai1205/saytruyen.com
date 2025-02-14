package vn.com.saytruyen.user_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.user_service.controller.LevelController;
import vn.com.saytruyen.user_service.request.LevelRequest;
import vn.com.saytruyen.user_service.response.LevelResponse;
import vn.com.saytruyen.user_service.service.LevelService;

/**
 * The type Level controller.
 */
@Component
public class LevelControllerImpl implements LevelController {

    private final LevelService levelService;

    /**
     * Instantiates a new Level controller.
     *
     * @param levelService the level service
     */
    @Autowired
    public LevelControllerImpl(LevelService levelService) {
        this.levelService = levelService;
    }

    @Override
    public ApiResponse<PageableResponse> getListLevel(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>(levelService.getListLevel(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> createLevel(LevelRequest levelRequest) {
        levelService.createLevel(levelRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateLevel(LevelRequest levelRequest, Long id) {
        levelService.updateLevel(levelRequest, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteLevel(Long id) {
        levelService.hardDeleteLevel(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<LevelResponse> getLevel(Long id) {
        return new ApiResponse<>(levelService.getLevel(id));
    }
}