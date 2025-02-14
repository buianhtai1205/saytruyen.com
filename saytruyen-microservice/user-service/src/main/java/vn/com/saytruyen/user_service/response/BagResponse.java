package vn.com.saytruyen.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Bag response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagResponse {

    private Long id;

    private Long number;

    private boolean isLock;

    private Long userId;

    private Long itemId;
}