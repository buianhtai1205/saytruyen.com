package vn.com.saytruyen.story_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.story_service.constant.StoryServiceConst;
import vn.com.saytruyen.story_service.converter.ChapterConverter;
import vn.com.saytruyen.story_service.model.Chapter;
import vn.com.saytruyen.story_service.repository.ChapterRepository;
import vn.com.saytruyen.story_service.request.ChapterRequest;
import vn.com.saytruyen.story_service.response.ChapterResponse;
import vn.com.saytruyen.story_service.service.ChapterService;

import java.util.List;
import java.util.Optional;

/**
 * The type Chapter service.
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public void createChapter(ChapterRequest chapterRequest) {
        Chapter chapter = ChapterConverter.INSTANCE.chapterRequestToChapter(chapterRequest);
        chapter.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        chapter.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        chapter.setPublishedAt(DateTimeUtils.getCurrentDateTime());
        chapter.setDeleted(Boolean.FALSE);
        chapterRepository.save(chapter);
    }

    @Override
    public void updateChapter(ChapterRequest chapterRequest, String chapterId) {
        Chapter existChapter = getExistingChapter(chapterId);
        ReflectionUtils.copyNonNullFields(chapterRequest, existChapter);
        existChapter.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        chapterRepository.save(existChapter);
    }

    @Override
    public void deleteChapter(String chapterId) {
        Chapter existChapter = getExistingChapter(chapterId);
        chapterRepository.delete(existChapter);
    }

    @Override
    public ChapterResponse getChapter(String chapterId) {
        return ChapterConverter.INSTANCE.chapterToChapterResponse(getExistingChapter(chapterId));
    }

    @Override
    public List<ChapterResponse> getAllChapter() {
        return ChapterConverter.INSTANCE.lstChapterToListChapterResponse(chapterRepository.findAll());
    }

    /**
     * Gets existing chapter.
     *
     * @param chapterId the chapter id
     * @return the existing chapter
     */
    public Chapter getExistingChapter(String chapterId) {
        Optional<Chapter> existChapter = chapterRepository.findById(chapterId);
        if (existChapter.isPresent()) {
            return existChapter.get();
        } else throw new ResourceNotFoundException(StoryServiceConst.CHAPTER_EN, chapterId);
    }
}
