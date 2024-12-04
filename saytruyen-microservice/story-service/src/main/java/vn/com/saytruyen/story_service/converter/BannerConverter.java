package vn.com.saytruyen.story_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.story_service.model.Banner;
import vn.com.saytruyen.story_service.request.BannerRequest;
import vn.com.saytruyen.story_service.response.BannerResponse;

import java.util.List;

/**
 * The interface Banner converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BannerConverter {

    /**
     * The constant INSTANCE.
     */
    BannerConverter INSTANCE = Mappers.getMapper(BannerConverter.class);

    /**
     * Banner request to banner banner.
     *
     * @param bannerRequest the banner request
     * @return the banner
     */
    Banner bannerRequestToBanner(BannerRequest bannerRequest);

    /**
     * Banner to banner response banner response.
     *
     * @param banner the banner
     * @return the banner response
     */
    BannerResponse bannerToBannerResponse(Banner banner);

    /**
     * Lst banner to lst banner response list.
     *
     * @param lstBanner the lst banner
     * @return the list
     */
    List<BannerResponse> lstBannerToLstBannerResponse(List<Banner> lstBanner);
}
