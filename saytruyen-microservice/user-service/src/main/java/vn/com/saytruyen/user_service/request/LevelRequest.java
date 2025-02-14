package vn.com.saytruyen.user_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Level request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelRequest {

    private String levelName;

    private String description;

    private Long fightingPointFrom;

    private Long fightingPointTo;

    private Long vitalEnergyPointFrom;

    private Long vitalEnergyPointTo;

    private Integer subLevel;

    private Boolean isHidden;
}