package vn.com.saytruyen.story_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Update story request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStoryRequest {

    private String storyId;

    private StoryRequest storyRequest;
}
