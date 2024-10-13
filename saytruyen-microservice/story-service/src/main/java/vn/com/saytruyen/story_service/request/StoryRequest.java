package vn.com.saytruyen.story_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Story request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoryRequest {

    private String name;

    private String slug;

    private String description;

    private String status;

    private String kind;

    private Long chapterCount;

    private Long chapterPerWeek;

    private Long commentCount;

    private Long reviewCount;

    private Long reviewCore;

    private Long authorId;

    private Long viewCount;

    private Long voteCount;

    private Long wordCount;

    private String others;
}
