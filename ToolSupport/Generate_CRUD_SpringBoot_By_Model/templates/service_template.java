package {{ package_name }}.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.constant.CommonConstants;
import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import io.github.buianhtai1205.saytruyen_common_service.utils.DateTimeUtils;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import {{ package_name }}.constant.ServiceConst;
import {{ package_name }}.converter.{{ entity_name }}Converter;
import {{ package_name }}.model.{{ entity_name }};
import {{ package_name }}.repository.{{ entity_name }}Repository;
import {{ package_name }}.request.{{ entity_name }}Request;
import {{ package_name }}.response.{{ entity_name }}Response;
import {{ package_name }}.service.{{ entity_name }}Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class {{ entity_name }}ServiceImpl implements {{ entity_name }}Service {

    @Autowired
    private {{ entity_name }}Repository {{ entity_name | lower }}Repository;

    @Override
    public PageableResponse getList{{ entity_name }}(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? CommonConstants.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? CommonConstants.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<{{ entity_name }}> lst{{ entity_name }} = {{ entity_name | lower }}Repository.findAll(pageable);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lst{{ entity_name }}.getTotalPages())
                .totalElements(lst{{ entity_name }}.getTotalElements())
                .data({{ entity_name }}Converter.INSTANCE.lst{{ entity_name }}ToLst{{ entity_name }}Response(lst{{ entity_name }}.getContent()))
                .build();
    }

    @Override
    @Transactional
    public void create{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower }}Request) {
        {{ entity_name }} {{ entity_name | lower }} = {{ entity_name }}Converter.INSTANCE.{{ entity_name | lower }}RequestTo{{ entity_name }}({{ entity_name | lower }}Request);
        {{ entity_name | lower }}.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        {{ entity_name | lower }}.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        {{ entity_name | lower }}.setDeleted(Boolean.FALSE);
        {{ entity_name | lower }}Repository.save({{ entity_name | lower }});
    }

    @Override
    @Transactional
    public void update{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower }}Request, {{ id_type}} id) {
        Optional<{{ entity_name }}> current{{ entity_name }} = {{ entity_name | lower }}Repository.findById(id);
        if (current{{ entity_name }}.isPresent() && Objects.nonNull({{ entity_name | lower }}Request)) {
            {{ entity_name }} {{ entity_name | lower }}ToUpdate = current{{ entity_name }}.get();
            ReflectionUtils.copyNonNullFields({{ entity_name | lower }}Request, {{ entity_name | lower }}ToUpdate);
            {{ entity_name | lower }}ToUpdate.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
            {{ entity_name | lower }}Repository.save({{ entity_name | lower }}ToUpdate);
        }
    }

    @Override
    @Transactional
    public void delete{{ entity_name }}({{ id_type}} id) {
        Optional<{{ entity_name }}> current{{ entity_name }} = {{ entity_name | lower }}Repository.findById(id);
        if (current{{ entity_name }}.isPresent()) {
            {{ entity_name }} {{ entity_name | lower }}ToDelete = current{{ entity_name }}.get();
            {{ entity_name | lower }}ToDelete.setDeleted(Boolean.TRUE);
            {{ entity_name | lower }}ToDelete.setDeletedAt(DateTimeUtils.getCurrentDateTime());
            {{ entity_name | lower }}Repository.save({{ entity_name | lower }}ToDelete);
        }
    }

    @Override
    public {{ entity_name }}Response get{{ entity_name }}({{ id_type}} id) {
        return {{ entity_name }}Converter.INSTANCE.{{ entity_name | lower }}To{{ entity_name }}Response(getExisting{{ entity_name }}(id));
    }

    @Override
    @Transactional
    public void hardDelete{{ entity_name }}({{ id_type}} id) {
        {{ entity_name }} {{ entity_name | lower }} = getExisting{{ entity_name }}(id);
        {{ entity_name | lower }}Repository.delete({{ entity_name | lower }});
    }

    public {{ entity_name }} getExisting{{ entity_name }}({{ id_type}} {{ entity_name | lower }}Id) {
        Optional<{{ entity_name }}> exist{{ entity_name }} = {{ entity_name | lower }}Repository.findById({{ entity_name | lower }}Id);
        if (exist{{ entity_name }}.isPresent()) {
            return exist{{ entity_name }}.get();
        } else throw new ResourceNotFoundException(ServiceConst.{{ entity_name | upper }}, {{ entity_name | lower }}Id);
    }
}
