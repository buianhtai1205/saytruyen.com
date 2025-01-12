package vn.com.saytruyen.admin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.admin_service.model.MCategory;

/**
 * The interface M category repository.
 */
@Repository
public interface MCategoryRepository extends JpaRepository<MCategory, Long> {
}
