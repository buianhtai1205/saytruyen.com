package vn.com.saytruyen.user_service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Equip location.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "equipLocation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equip> equips;
}
