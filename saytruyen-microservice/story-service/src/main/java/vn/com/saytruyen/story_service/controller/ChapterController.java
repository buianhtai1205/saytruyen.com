package vn.com.saytruyen.story_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The interface Chapter controller.
 */
@RestController
@RequestMapping("/api/story/chapter")
public interface ChapterController {

    /**
     * Create chapter.
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void createChapter();

    /**
     * Update chapter.
     *
     * @param chapterId the chapter id
     */
    @PutMapping("/update/{chapterId}")
    @ResponseStatus(HttpStatus.OK)
    void updateChapter(@PathVariable("chapterId") int chapterId);

    /**
     * Delete chapter.
     *
     * @param chapterId the chapter id
     */
    @DeleteMapping("/delete/{chapterId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteChapter(@PathVariable("chapterId") int chapterId);

    /**
     * Gets all chapter.
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    void getAllChapter();
}
