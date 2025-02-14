package vn.com.saytruyen.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.user_service.model.EquipLocation;

/**
 * The interface Equip location repository.
 */
@Repository
public interface EquipLocationRepository extends JpaRepository<EquipLocation, Long> {
}