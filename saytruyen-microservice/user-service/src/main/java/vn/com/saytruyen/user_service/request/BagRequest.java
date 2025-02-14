package vn.com.saytruyen.user_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Bag request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagRequest {

    private Long number;

    private boolean isLock;

    private Long userId;

    private Long itemId;
}