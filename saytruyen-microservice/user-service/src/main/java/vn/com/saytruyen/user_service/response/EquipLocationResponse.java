package vn.com.saytruyen.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Equip location response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipLocationResponse {

    private Long id;

    private String name;
}