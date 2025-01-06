package vn.com.saytruyen.admin_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Banner request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerRequest {

    private String name;

    private String url;

    private String bannerType;

    private String bannerLinkedId;

    private String bgDesktop;

    private String bannerDesktop;

    private String bannerMobile;

    private String others;
}
