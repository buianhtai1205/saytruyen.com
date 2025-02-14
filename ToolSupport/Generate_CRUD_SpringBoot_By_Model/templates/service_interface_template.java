package {{ package_name }}.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import {{ package_name }}.request.{{ entity_name }}Request;
import {{ package_name }}.response.{{ entity_name }}Response;

public interface {{ entity_name }}Service {

    PageableResponse getList{{ entity_name }}(Integer pageNumber, Integer pageSize);

    void create{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower_first }}Request);

    void update{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower_first }}Request, {{ id_type}} {{ entity_name | lower_first }}Id);

    void delete{{ entity_name }}({{ id_type}} id);

    {{ entity_name }}Response get{{ entity_name }}({{ id_type}} id);

    void hardDelete{{ entity_name }}({{ id_type}} id);
}
