package {{ package_name }}.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import {{ package_name }}.request.{{ entity_name }}Request;
import {{ package_name }}.response.{{ entity_name }}Response;

@RestController
@RequestMapping("/api/{{ endpoint | default(entity_name|lower) }}")
public interface {{ entity_name }}Controller {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PageableResponse> getList{{ entity_name }}(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> create{{ entity_name }}(@RequestBody {{ entity_name }}Request {{ entity_name | lower }}Request);

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> update{{ entity_name }}(@RequestBody {{ entity_name }}Request {{ entity_name | lower }}Request,
                                                @PathVariable {{ id_type}} id);

    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> delete{{ entity_name }}(@PathVariable {{ id_type}} id);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<{{ entity_name }}Response> get{{ entity_name }}(@PathVariable {{ id_type}} id);
}