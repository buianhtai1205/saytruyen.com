package io.github.buianhtai1205.saytruyen_common_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Pageable response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalElements;

    private Object data;
}

