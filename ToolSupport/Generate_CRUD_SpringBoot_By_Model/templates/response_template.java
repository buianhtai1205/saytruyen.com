package {{ package_name }}.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class {{ entity_name }}Response {

    {% for field in fields %}
    private {{ field.type }} {{ field.name }};
    
    {% endfor %}
}
