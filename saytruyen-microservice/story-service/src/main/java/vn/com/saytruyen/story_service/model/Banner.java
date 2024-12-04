package vn.com.saytruyen.story_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * The type Banner.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "Banner")
public class Banner {

    @Id
    private String id;

    private String name;

    private String url;

    private String bannerType;

    private String bannerLinkedId;

    private String bgDesktop;

    private String bannerDesktop;

    private String bannerMobile;

    /**
     * The date and time when the chapter was created.
     */
    private LocalDateTime createdAt;

    /**
     * The date and time when the chapter was last updated.
     */
    private LocalDateTime updatedAt;

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
