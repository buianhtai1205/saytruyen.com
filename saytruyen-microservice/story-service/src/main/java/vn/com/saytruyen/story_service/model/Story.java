package vn.com.saytruyen.story_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * The type Story.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "Story")
public class Story {

    @Id
    private String id;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime newChapAt;

    private LocalDateTime publishedAt;

    private LocalDateTime deletedAt;

    private boolean isDeleted;

    private String others;
}
