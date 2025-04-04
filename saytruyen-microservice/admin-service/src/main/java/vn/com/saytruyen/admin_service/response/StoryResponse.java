package vn.com.saytruyen.admin_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Story response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoryResponse {

    private String id;

    private String name;

    private String slug;

    private String description;

    private List<String> poster;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime newChapAt;

    private LocalDateTime publishedAt;

    private String others;
}
