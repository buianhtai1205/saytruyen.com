package vn.com.saytruyen.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Level response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelResponse {

    private Long id;

    private String levelName;

    private String description;

    private Long fightingPointFrom;

    private Long fightingPointTo;

    private Long vitalEnergyPointFrom;

    private Long vitalEnergyPointTo;

    private Integer subLevel;

    private Boolean isHidden;
}