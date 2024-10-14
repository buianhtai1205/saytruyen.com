package vn.com.saytruyen.story_service.service;

import vn.com.saytruyen.story_service.request.ChapterRequest;
import vn.com.saytruyen.story_service.response.ChapterResponse;

import java.util.List;

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
     * @return the all chapter
     */
    List<ChapterResponse> getAllChapter();
}
