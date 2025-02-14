package {{ package_name }}.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import {{ package_name }}.model.{{ entity_name }};
import {{ package_name }}.request.{{ entity_name }}Request;
import {{ package_name }}.response.{{ entity_name }}Response;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface {{ entity_name }}Converter {

    {{ entity_name }}Converter INSTANCE = Mappers.getMapper({{ entity_name }}Converter.class);

    {{ entity_name }} {{ entity_name  | lower_first }}RequestTo{{ entity_name }}({{ entity_name }}Request {{ entity_name  | lower_first }}Request);

    {{ entity_name }}Response {{ entity_name  | lower_first }}To{{ entity_name }}Response({{ entity_name }} {{ entity_name  | lower_first }});

    List<{{ entity_name }}Response> lst{{ entity_name }}ToLst{{ entity_name }}Response(List<{{ entity_name }}> lst{{ entity_name }});
}
