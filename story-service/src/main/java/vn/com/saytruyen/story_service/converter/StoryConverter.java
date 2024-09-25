package vn.com.saytruyen.story_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.story_service.model.Story;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.response.StoryResponse;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface StoryConverter {

    StoryConverter INSTANCE = Mappers.getMapper(StoryConverter.class);

    Story storyRequestToStory(StoryRequest storyRequest);

    List<StoryResponse> lstStoryToLstStoryResponse(List<Story> lstStory);
}
