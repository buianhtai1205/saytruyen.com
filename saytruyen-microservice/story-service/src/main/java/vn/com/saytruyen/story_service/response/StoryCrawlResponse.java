package vn.com.saytruyen.story_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Story crawl response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoryCrawlResponse {

    private String id;

    private String slug;

    private Long chapterCount;
}
