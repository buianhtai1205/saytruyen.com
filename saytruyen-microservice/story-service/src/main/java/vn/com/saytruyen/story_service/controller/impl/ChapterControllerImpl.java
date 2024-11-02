package vn.com.saytruyen.story_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.story_service.controller.ChapterController;
import vn.com.saytruyen.story_service.request.ChapterRequest;
import vn.com.saytruyen.story_service.response.ChapterResponse;
import vn.com.saytruyen.story_service.service.ChapterService;

/**
 * The type Chapter controller.
 */
@Component
public class ChapterControllerImpl implements ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Override
    public ApiResponse<Boolean> createChapter(ChapterRequest chapterRequest) {
        chapterService.createChapter(chapterRequest);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> updateChapter(ChapterRequest chapterRequest, String chapterId) {
        chapterService.updateChapter(chapterRequest, chapterId);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> deleteChapter(String chapterId) {
        chapterService.deleteChapter(chapterId);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<ChapterResponse> getChapter(String chapterId) {
        return new ApiResponse<>(chapterService.getChapter(chapterId));
    }

    @Override
    public ApiResponse<PageableResponse> getAllChapter(Integer pageNumber, Integer pageSize, String storyId) {
        return new ApiResponse<>(chapterService.getAllChapter(pageNumber, pageSize, storyId));
    }
}
