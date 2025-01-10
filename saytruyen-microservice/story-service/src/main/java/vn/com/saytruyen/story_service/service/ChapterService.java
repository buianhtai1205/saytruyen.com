package vn.com.saytruyen.story_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import vn.com.saytruyen.story_service.request.ChapterRequest;
import vn.com.saytruyen.story_service.response.ChapterResponse;

/**
 * The interface Chapter service.
 */
public interface ChapterService {

    /**
     * Create chapter.
     *
     * @param chapterRequest the chapter request
     */
    void createChapter(ChapterRequest chapterRequest);

    /**
     * Update chapter.
     *
     * @param chapterRequest the chapter request
     * @param chapterId      the chapter id
     */
    void updateChapter(ChapterRequest chapterRequest, String chapterId);

    /**
     * Delete chapter.
     *
     * @param chapterId the chapter id
     */
    void deleteChapter(String chapterId);

    /**
     * Gets chapter.
     *
     * @param chapterId the chapter id
     * @return the chapter
     */
    ChapterResponse getChapter(String chapterId);

    /**
     * Gets all chapter.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @param storyId    the story id
     * @return the all chapter
     */
    PageableResponse getAllChapter(Integer pageNumber, Integer pageSize, String storyId);

    /**
     * Hard delete chapter.
     *
     * @param chapterId the chapter id
     */
    void hardDeleteChapter(String chapterId);
}
