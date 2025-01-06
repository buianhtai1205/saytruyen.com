package vn.com.saytruyen.admin_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * The type Chapter request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterRequest {

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
     * The unique identifier of the story the chapter belongs to.
     */
    private String storyId;

    /**
     * Additional information about the chapter.
     */
    private String others;
}
