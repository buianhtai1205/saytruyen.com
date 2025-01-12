package vn.com.saytruyen.admin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.admin_service.model.MDataConfig;

/**
 * The interface M data config repository.
 */
@Repository
public interface MDataConfigRepository extends JpaRepository<MDataConfig, Long> {
}
