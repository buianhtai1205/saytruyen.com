package vn.com.saytruyen.story_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a chapter in a story.
 *
 * @author buianhtai1205
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "Chapter")
public class Chapter {

    /**
     * The unique identifier of the chapter.
     */
    @Id
    private String id;

    /**
     * The name of the chapter.
     */
    private String name;

    /**
     * The content of the chapter.
     */
    private String content;

    /**
     * Indicates whether the chapter is locked or not.
     */
    private boolean isLocked;

    /**
     * The price required to unlock the chapter.
     */
    private BigDecimal unlockPrice;

    /**
     * The number of views the chapter has received.
     */
    private Long viewCount;

    /**
     * The number of words in the chapter.
     */
    private Long wordCount;

    /**
     * The unique identifier of the story the chapter belongs to.
     */
    private String storyId;

    /**
     * The date and time when the chapter was created.
     */
    private LocalDateTime createdAt;

    /**
     * The date and time when the chapter was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * The date and time when the chapter was published.
     */
    private LocalDateTime publishedAt;

    /**
     * The date and time when the chapter was deleted.
     */
    private LocalDateTime deletedAt;

    /**
     * Indicates whether the chapter is deleted or not.
     */
    private boolean isDeleted;

    /**
     * Additional information about the chapter.
     */
    private String others;
}
