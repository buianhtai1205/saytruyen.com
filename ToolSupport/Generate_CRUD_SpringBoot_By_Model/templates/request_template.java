package {{ package_name }}.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class {{ entity_name }}Request {
    {% for field in fields if not field.isKey %}
    {% if field.isRelationship %}

    private {{ field.idType }} {{ field.name }};
    {% else %}

    private {{ field.type }} {{ field.name }};
    {% endif %}
    {% endfor %}
}
