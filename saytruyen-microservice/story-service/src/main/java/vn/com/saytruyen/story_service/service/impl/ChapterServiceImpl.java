package vn.com.saytruyen.story_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.exception.BusinessException;
import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.saytruyen.story_service.constant.StoryServiceConst;
import vn.com.saytruyen.story_service.constant.StoryServiceMessage;
import vn.com.saytruyen.story_service.converter.ChapterConverter;
import vn.com.saytruyen.story_service.model.Chapter;
import vn.com.saytruyen.story_service.repository.ChapterRepository;
import vn.com.saytruyen.story_service.request.ChapterRequest;
import vn.com.saytruyen.story_service.response.ChapterResponse;
import vn.com.saytruyen.story_service.service.ChapterService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Chapter service.
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    @Transactional
    public void createChapter(ChapterRequest chapterRequest) {
        Chapter chapter = ChapterConverter.INSTANCE.chapterRequestToChapter(chapterRequest);
        chapter.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        chapter.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        chapter.setPublishedAt(DateTimeUtils.getCurrentDateTime());
        chapter.setDeleted(Boolean.FALSE);
        chapterRepository.save(chapter);
    }

    @Override
    @Transactional
    public void updateChapter(ChapterRequest chapterRequest, String chapterId) {
        Chapter existChapter = getExistingChapter(chapterId);
        ReflectionUtils.copyNonNullFields(chapterRequest, existChapter);
        existChapter.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        chapterRepository.save(existChapter);
    }

    @Override
    @Transactional
    public void deleteChapter(String chapterId) {
        Chapter existChapter = getExistingChapter(chapterId);
        chapterRepository.delete(existChapter);
    }

    @Override
    public ChapterResponse getChapter(String chapterId) {
        return ChapterConverter.INSTANCE.chapterToChapterResponse(getExistingChapter(chapterId));
    }

    @Override
    public PageableResponse getAllChapter(Integer pageNumber, Integer pageSize, String storyId) {
        pageNumber = Objects.isNull(pageNumber) ? StoryServiceConst.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? StoryServiceConst.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (StringUtils.isNullOrEmpty(storyId)) {
            throw new BusinessException(messageSource.getMessage(
                    StoryServiceMessage.ST_E000001,
                    null,
                    LocaleContextHolder.getLocale()
            ));
        }

        Page<Chapter> lstChapter = chapterRepository.findAllByStoryId(pageable, storyId);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lstChapter.getTotalPages())
                .totalElements(lstChapter.getTotalElements())
                .data(ChapterConverter.INSTANCE.lstChapterToListChapterResponse(lstChapter.getContent()))
                .build();
    }

    @Override
    @Transactional
    public void hardDeleteChapter(String chapterId) {
        Chapter existChapter = getExistingChapter(chapterId);
        chapterRepository.delete(existChapter);
    }

    @Override
    public List<ChapterResponse> getListChapterSimpleByStoryId(String storyId) {
        List<Chapter> lstChapter = chapterRepository.getListChapterSimpleByStoryId(storyId);
        return ChapterConverter.INSTANCE.lstChapterToListChapterResponse(lstChapter);
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
