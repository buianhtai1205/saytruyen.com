package vn.com.saytruyen.admin_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The type Banner response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerResponse {

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
     * Additional information about the chapter.
     */
    private String others;
}
