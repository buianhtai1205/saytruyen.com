package vn.com.saytruyen.admin_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Page info request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoRequest {

    private Integer pageNumber;

    private Integer pageSize;
}
