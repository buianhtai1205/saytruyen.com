package vn.com.saytruyen.admin_service.service;

import vn.com.saytruyen.admin_service.request.ChapterRequest;

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
    Object getChapter(String chapterId);

    /**
     * Gets all chapter.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @param storyId    the story id
     * @return the all chapter
     */
    Object getAllChapter(Integer pageNumber, Integer pageSize, String storyId);
}
