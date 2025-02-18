package vn.com.saytruyen.admin_service.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.com.saytruyen.admin_service.request.ChapterRequest;

/**
 * The interface Chapter controller.
 */
@RestController
@RequestMapping("/api/admin-service/chapter")
public interface ManageChapterController {

    /**
     * Create chapter.
     *
     * @param chapterRequest the chapter request
     * @return the api response
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createChapter(@RequestBody ChapterRequest chapterRequest);

    /**
     * Update chapter.
     *
     * @param chapterRequest the chapter request
     * @param chapterId      the chapter id
     * @return the api response
     */
    @PutMapping("/update/{chapterId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateChapter(@RequestBody ChapterRequest chapterRequest, @PathVariable("chapterId") String chapterId);

    /**
     * Delete chapter.
     *
     * @param chapterId the chapter id
     * @return the api response
     */
    @DeleteMapping("/delete/{chapterId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteChapter(@PathVariable("chapterId") String chapterId);

    /**
     * Gets chapter.
     *
     * @param chapterId the chapter id
     * @return the chapter
     */
    @GetMapping("/{chapterId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Object> getChapter(@PathVariable("chapterId") String chapterId);

    /**
     * Gets all chapter.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @param storyId    the story id
     * @return the all chapter
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Object> getAllChapter(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                      @RequestParam(value = "storyId", required = false) String storyId);
}
