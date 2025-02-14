package vn.com.saytruyen.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.user_service.model.Bag;

/**
 * The interface Bag repository.
 */
@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {
}