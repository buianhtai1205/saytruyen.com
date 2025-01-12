package vn.com.saytruyen.admin_service.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.admin_service.controller.ManageChapterController;
import vn.com.saytruyen.admin_service.request.ChapterRequest;
import vn.com.saytruyen.admin_service.service.ChapterService;

/**
 * The type Manager chapter controller.
 */
@Component
public class ManageChapterControllerImpl implements ManageChapterController {

    private final ChapterService chapterService;

    /**
     * Instantiates a new Manager chapter controller.
     *
     * @param chapterService the chapter service
     */
    @Autowired
    public ManageChapterControllerImpl(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

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
    public ApiResponse<Object> getChapter(String chapterId) {
        return new ApiResponse<>(chapterService.getChapter(chapterId));
    }

    @Override
    public ApiResponse<Object> getAllChapter(Integer pageNumber, Integer pageSize, String storyId) {
        return new ApiResponse<>(chapterService.getAllChapter(pageNumber, pageSize, storyId));
    }
}
