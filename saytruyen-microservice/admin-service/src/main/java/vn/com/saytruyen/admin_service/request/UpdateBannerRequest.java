package vn.com.saytruyen.admin_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Update banner request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBannerRequest {

    private String bannerId;

    private BannerRequest bannerRequest;
}
