package vn.com.saytruyen.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The type Item response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private BigDecimal subPrice;

    private Long essencePoint;

    private Long vitalEnergyPoint;

    private Long spiritPoint;

    private boolean isDeleted;

    private Long deletedBy;

    private LocalDateTime deletedAt;

    private String others;
}