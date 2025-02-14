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

    private final {{ entity_name }}Repository {{ entity_name | lower_first }}Repository;

    @Autowired
    public {{ entity_name }}ServiceImpl({{ entity_name }}Repository {{ entity_name | lower_first }}Repository) {
        this.{{ entity_name | lower_first }}Repository = {{ entity_name | lower_first }}Repository;
    }

    @Override
    public PageableResponse getList{{ entity_name }}(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? CommonConstants.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? CommonConstants.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<{{ entity_name }}> lst{{ entity_name }} = {{ entity_name | lower_first }}Repository.findAll(pageable);

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
    public void create{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower_first }}Request) {
        {{ entity_name }} {{ entity_name | lower_first }} = {{ entity_name }}Converter.INSTANCE.{{ entity_name | lower_first }}RequestTo{{ entity_name }}({{ entity_name | lower_first }}Request);
        {{ entity_name | lower_first }}.setCreatedAt(DateTimeUtils.getCurrentDateTime());
        {{ entity_name | lower_first }}.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
        {{ entity_name | lower_first }}.setDeleted(Boolean.FALSE);
        {{ entity_name | lower_first }}Repository.save({{ entity_name | lower_first }});
    }

    @Override
    @Transactional
    public void update{{ entity_name }}({{ entity_name }}Request {{ entity_name | lower_first }}Request, {{ id_type}} id) {
        Optional<{{ entity_name }}> current{{ entity_name }} = {{ entity_name | lower_first }}Repository.findById(id);
        if (current{{ entity_name }}.isPresent() && Objects.nonNull({{ entity_name | lower_first }}Request)) {
            {{ entity_name }} {{ entity_name | lower_first }}ToUpdate = current{{ entity_name }}.get();
            ReflectionUtils.copyNonNullFields({{ entity_name | lower_first }}Request, {{ entity_name | lower_first }}ToUpdate);
            {{ entity_name | lower_first }}ToUpdate.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
            {{ entity_name | lower_first }}Repository.save({{ entity_name | lower_first }}ToUpdate);
        }
    }

    @Override
    @Transactional
    public void delete{{ entity_name }}({{ id_type}} id) {
        Optional<{{ entity_name }}> current{{ entity_name }} = {{ entity_name | lower_first }}Repository.findById(id);
        if (current{{ entity_name }}.isPresent()) {
            {{ entity_name }} {{ entity_name | lower_first }}ToDelete = current{{ entity_name }}.get();
            {{ entity_name | lower_first }}ToDelete.setDeleted(Boolean.TRUE);
            {{ entity_name | lower_first }}ToDelete.setDeletedAt(DateTimeUtils.getCurrentDateTime());
            {{ entity_name | lower_first }}Repository.save({{ entity_name | lower_first }}ToDelete);
        }
    }

    @Override
    public {{ entity_name }}Response get{{ entity_name }}({{ id_type}} id) {
        return {{ entity_name }}Converter.INSTANCE.{{ entity_name | lower_first }}To{{ entity_name }}Response(getExisting{{ entity_name }}(id));
    }

    @Override
    @Transactional
    public void hardDelete{{ entity_name }}({{ id_type}} id) {
        {{ entity_name }} {{ entity_name | lower_first }} = getExisting{{ entity_name }}(id);
        {{ entity_name | lower_first }}Repository.delete({{ entity_name | lower_first }});
    }

    public {{ entity_name }} getExisting{{ entity_name }}({{ id_type}} {{ entity_name | lower_first }}Id) {
        Optional<{{ entity_name }}> exist{{ entity_name }} = {{ entity_name | lower_first }}Repository.findById({{ entity_name | lower_first }}Id);
        if (exist{{ entity_name }}.isPresent()) {
            return exist{{ entity_name }}.get();
        } else throw new ResourceNotFoundException(ServiceConst.{{ entity_name | upper }}, {{ entity_name | lower_first }}Id);
    }
}
