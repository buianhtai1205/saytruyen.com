package io.github.buianhtai1205.saytruyen_common_service.response;

import io.github.buianhtai1205.saytruyen_common_service.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Api response.
 *
 * @param <T> the type parameter
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    @Builder.Default
    private Integer code = CommonConstants.SUCCESS_CODE;

    private String messageId;

    @Builder.Default
    private String message = CommonConstants.SUCCESS_REQUEST;

    private T data;

    /**
     * Instantiates a new Api response.
     *
     * @param data the data
     */
    public ApiResponse(T data) {
        this.code = CommonConstants.SUCCESS_CODE;
        this.message = CommonConstants.SUCCESS_REQUEST;
        this.data = data;
    }
}
