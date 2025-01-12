package vn.com.saytruyen.admin_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Master data config request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterDataConfigRequest {

    private String configName;

    private String configValue;

    private String configDescription;
}
