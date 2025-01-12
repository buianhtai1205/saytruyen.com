package vn.com.saytruyen.admin_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The type M category.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "M_CATEGORY")
public class MCategory extends CommonField {

    @Id
    private Long id;

    private String categoryName;

    private String categoryDescription;

    private String categoryImage;
}
