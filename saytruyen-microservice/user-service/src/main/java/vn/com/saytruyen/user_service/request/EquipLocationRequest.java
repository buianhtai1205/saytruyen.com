package vn.com.saytruyen.user_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Equip location request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipLocationRequest {

    private String name;
}