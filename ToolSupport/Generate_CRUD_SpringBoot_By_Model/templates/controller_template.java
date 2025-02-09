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

    @Autowired
    private {{ entity_name }}Service {{ entity_name | lower }}Service;


    @Override
    public ApiResponse<PageableResponse> getList{{ entity_name }}(Integer pageNumber, Integer pageSize) {
        return new ApiResponse<>({{ entity_name  | lower }}Service.getList{{ entity_name }}(pageNumber, pageSize));
    }

    @Override
    public ApiResponse<Boolean> create{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower }}Request) {
        {{ entity_name | lower }}Service.create{{ entity_name }}({{ entity_name | lower }}Request);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> update{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower }}Request, {{ id_type}} id) {
        {{ entity_name | lower }}Service.update{{ entity_name }}({{ entity_name | lower }}Request, id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> delete{{ entity_name }}({{ id_type}} id) {
        {{ entity_name | lower }}Service.delete{{ entity_name }}(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @Override
    public ApiResponse<{{ entity_name }}Response> get{{ entity_name }}({{ id_type}} id) {
        return new ApiResponse<>({{ entity_name | lower }}Service.get{{ entity_name }}(id));
    }
}
