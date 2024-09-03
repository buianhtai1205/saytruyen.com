package vn.com.saytruyen.story_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "Chapter")
public class Chapter {

    @Id
    private Long id;

    private String name;

    private String content;

    private boolean isLocked;

    private BigDecimal unlockPrice;

    private Long viewCount;

    private Long wordCount;

    private Long storyId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime publishedAt;

    private LocalDateTime deletedAt;

    private boolean isDeleted;
}
