package vn.com.saytruyen.admin_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Get all chapter request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllChapterRequest {

    private String storyId;

    private PageInfoRequest pageInfoRequest;
}
