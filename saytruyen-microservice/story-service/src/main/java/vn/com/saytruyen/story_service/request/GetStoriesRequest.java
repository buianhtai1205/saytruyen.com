package vn.com.saytruyen.story_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Get stories request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStoriesRequest {

    private Integer pageNumber;

    private Integer pageSize;
}
