package vn.com.saytruyen.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.user_service.model.Role;

/**
 * The interface Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find by role name role.
     *
     * @param value the value
     * @return the role
     */
    Role findByRoleName(String value);
}
