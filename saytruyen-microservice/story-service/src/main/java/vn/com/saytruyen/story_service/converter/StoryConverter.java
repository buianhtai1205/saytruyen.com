package vn.com.saytruyen.story_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.story_service.model.Story;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryCrawlResponse;
import vn.com.saytruyen.story_service.response.StoryResponse;

import java.util.List;

/**
 * The interface Story converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface StoryConverter {

    /**
     * The constant INSTANCE.
     */
    StoryConverter INSTANCE = Mappers.getMapper(StoryConverter.class);

    /**
     * Story request to story story.
     *
     * @param storyRequest the story request
     * @return the story
     */
    Story storyRequestToStory(StoryRequest storyRequest);

    /**
     * Lst story to lst story response list.
     *
     * @param lstStory the lst story
     * @return the list
     */
    List<StoryResponse> lstStoryToLstStoryResponse(List<Story> lstStory);

    /**
     * Lst story to lst story crawl response list.
     *
     * @param lstStory the lst story
     * @return the list
     */
    List<StoryCrawlResponse> lstStoryToLstStoryCrawlResponse(List<Story> lstStory);

    /**
     * Story to story response story response.
     *
     * @param story the story
     * @return the story response
     */
    StoryResponse storyToStoryResponse(Story story);
}
