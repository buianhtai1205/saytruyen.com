package vn.com.saytruyen.user_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.constant.CommonConstants;
import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import io.github.buianhtai1205.saytruyen_common_service.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.saytruyen.user_service.constant.UserServiceConst;
import vn.com.saytruyen.user_service.converter.LevelConverter;
import vn.com.saytruyen.user_service.model.Level;
import vn.com.saytruyen.user_service.repository.LevelRepository;
import vn.com.saytruyen.user_service.request.LevelRequest;
import vn.com.saytruyen.user_service.response.LevelResponse;
import vn.com.saytruyen.user_service.service.LevelService;

import java.util.Objects;
import java.util.Optional;

/**
 * The type Level service.
 */
@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    /**
     * Instantiates a new Level service.
     *
     * @param levelRepository the level repository
     */
    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public PageableResponse getListLevel(Integer pageNumber, Integer pageSize) {
        pageNumber = Objects.isNull(pageNumber) ? CommonConstants.PAGE_NUMBER_DEFAULT : pageNumber;
        pageSize = Objects.isNull(pageSize) ? CommonConstants.PAGE_SIZE_DEFAULT : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Level> lstLevel = levelRepository.findAll(pageable);

        return PageableResponse.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(lstLevel.getTotalPages())
                .totalElements(lstLevel.getTotalElements())
                .data(LevelConverter.INSTANCE.lstLevelToLstLevelResponse(lstLevel.getContent()))
                .build();
    }

    @Override
    @Transactional
    public void createLevel(LevelRequest levelRequest) {
        Level level = LevelConverter.INSTANCE.levelRequestToLevel(levelRequest);
        levelRepository.save(level);
    }

    @Override
    @Transactional
    public void updateLevel(LevelRequest levelRequest, Long id) {
        Optional<Level> currentLevel = levelRepository.findById(id);
        if (currentLevel.isPresent() && Objects.nonNull(levelRequest)) {
            Level levelToUpdate = currentLevel.get();
            ReflectionUtils.copyNonNullFields(levelRequest, levelToUpdate);
            levelRepository.save(levelToUpdate);
        }
    }

    @Override
    public LevelResponse getLevel(Long id) {
        return LevelConverter.INSTANCE.levelToLevelResponse(getExistingLevel(id));
    }

    @Override
    @Transactional
    public void hardDeleteLevel(Long id) {
        Level level = getExistingLevel(id);
        levelRepository.delete(level);
    }

    /**
     * Gets existing level.
     *
     * @param levelId the level id
     * @return the existing level
     */
    public Level getExistingLevel(Long levelId) {
        Optional<Level> existLevel = levelRepository.findById(levelId);
        if (existLevel.isPresent()) {
            return existLevel.get();
        } else throw new ResourceNotFoundException(UserServiceConst.LEVEL, levelId);
    }
}