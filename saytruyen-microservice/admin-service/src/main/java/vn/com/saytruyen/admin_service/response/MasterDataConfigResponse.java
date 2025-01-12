package vn.com.saytruyen.admin_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.saytruyen.admin_service.model.CommonField;

/**
 * The type Master data config response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterDataConfigResponse extends CommonField {

    private Long id;

    private String configName;

    private String configValue;

    private String configDescription;
}
