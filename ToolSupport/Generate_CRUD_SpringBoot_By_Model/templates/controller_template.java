package {{ package_name }}.controller.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import {{ package_name }}.controller.{{ entity_name }}Controller;
import {{ package_name }}.request.{{ entity_name }}Request;
import {{ package_name }}.response.{{ entity_name }}Response;
import {{ package_name }}.service.{{ entity_name }}Service;

@Component
public class {{ entity_name }}ControllerImpl implements {{ entity_name }}Controller {

    private final {{ entity_name }}Service {{ entity_name | lower_first }}Service;

    @Autowired
    public {{ entity_name }}ControllerImpl({{ entity_name }}Service {{ entity_name | lower_first }}Service) {
        this.{{ entity_name | lower_first }}Service = {{ entity_name | lower_first }}Service;
    }

    @Override
    public ApiResponse<PageableResponse> getList{{ entity_name }}(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>({{ entity_name  | lower_first }}Service.getList{{ entity_name }}(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> create{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower_first }}Request) {
        {{ entity_name | lower_first }}Service.create{{ entity_name }}({{ entity_name | lower_first }}Request);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> update{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower_first }}Request, {{ id_type}} id) {
        {{ entity_name | lower_first }}Service.update{{ entity_name }}({{ entity_name | lower_first }}Request, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> delete{{ entity_name }}({{ id_type}} id) {
        {{ entity_name | lower_first }}Service.delete{{ entity_name }}(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<{{ entity_name }}Response> get{{ entity_name }}({{ id_type}} id) {
        return new ApiResponse<>({{ entity_name | lower_first }}Service.get{{ entity_name }}(id));
    }
}
