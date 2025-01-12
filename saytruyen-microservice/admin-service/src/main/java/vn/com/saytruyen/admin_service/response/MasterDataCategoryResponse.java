package vn.com.saytruyen.admin_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.saytruyen.admin_service.model.CommonField;

/**
 * The type Master data category response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterDataCategoryResponse extends CommonField {

    private Long id;

    private String categoryName;

    private String categoryDescription;

    private String categoryImage;
}
