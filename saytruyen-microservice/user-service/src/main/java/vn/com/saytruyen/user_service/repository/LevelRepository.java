package vn.com.saytruyen.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.user_service.model.Level;

/**
 * The interface Level repository.
 */
@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}