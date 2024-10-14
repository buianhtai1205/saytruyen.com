package vn.com.saytruyen.story_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.story_service.model.Chapter;
import vn.com.saytruyen.story_service.request.ChapterRequest;
import vn.com.saytruyen.story_service.response.ChapterResponse;

import java.util.List;

/**
 * The interface Chapter converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ChapterConverter {

    /**
     * The constant INSTANCE.
     */
    ChapterConverter INSTANCE = Mappers.getMapper(ChapterConverter.class);

    /**
     * Chapter request to chapter chapter.
     *
     * @param chapterRequest the chapter request
     * @return the chapter
     */
    Chapter chapterRequestToChapter(ChapterRequest chapterRequest);

    /**
     * Chapter to chapter response chapter response.
     *
     * @param chapter the chapter
     * @return the chapter response
     */
    ChapterResponse chapterToChapterResponse(Chapter chapter);

    /**
     * Lst chapter to list chapter response list.
     *
     * @param lstChapter the lst chapter
     * @return the list
     */
    List<ChapterResponse> lstChapterToListChapterResponse(List<Chapter> lstChapter);
}
