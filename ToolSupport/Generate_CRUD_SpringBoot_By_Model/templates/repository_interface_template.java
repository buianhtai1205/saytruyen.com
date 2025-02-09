package {{ package_name }}.repository;

{% if orm_type == "Jpa" %}
import org.springframework.data.jpa.repository.JpaRepository;
{% elif orm_type == "Mongo" %}
import org.springframework.data.mongodb.repository.MongoRepository;
{% endif %}
import org.springframework.stereotype.Repository;
import {{ package_name }}.model.{{ entity_name }};

@Repository
public interface {{ entity_name }}Repository extends {{ orm_type }}Repository<{{ entity_name }}, {{ id_type }}> {
}
