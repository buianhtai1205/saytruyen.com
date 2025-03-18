package vn.com.saytruyen.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.user_service.model.UserRole;

/**
 * The interface User role repository.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
