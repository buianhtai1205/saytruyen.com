package vn.com.saytruyen.admin_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Master data category request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterDataCategoryRequest {

    private String categoryName;

    private String categoryDescription;

    private String categoryImage;
}
