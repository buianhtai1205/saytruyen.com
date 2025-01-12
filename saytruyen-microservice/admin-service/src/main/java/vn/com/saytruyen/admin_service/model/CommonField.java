package vn.com.saytruyen.admin_service.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * The type Common field.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class CommonField {

    private boolean isDeleted;

    private Long createdBy;

    private Long updatedBy;

    private Long deletedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String others;
}
