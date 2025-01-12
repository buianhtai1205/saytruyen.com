package vn.com.saytruyen.story_service.service;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.saytruyen.story_service.model.Chapter;
import vn.com.saytruyen.story_service.repository.ChapterRepository;
import vn.com.saytruyen.story_service.request.ChapterRequest;
import vn.com.saytruyen.story_service.response.ChapterResponse;
import vn.com.saytruyen.story_service.service.impl.ChapterServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Chapter service impl test.
 */
class ChapterServiceImplTest {

    @Mock
    private ChapterRepository chapterRepository;

    @InjectMocks
    private ChapterServiceImpl chapterService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test create chapter.
     */
    @Test
    void testCreateChapter() {
        ChapterRequest chapterRequest = new ChapterRequest();
        chapterService.createChapter(chapterRequest);
        verify(chapterRepository, times(1)).save(any(Chapter.class));
    }

    /**
     * Test update chapter.
     */
    @Test
    void testUpdateChapter() {
        ChapterRequest chapterRequest = new ChapterRequest();
        Chapter chapter = new Chapter();
        when(chapterRepository.findById(anyString())).thenReturn(Optional.of(chapter));

        chapterService.updateChapter(chapterRequest, "1");

        verify(chapterRepository, times(1)).save(any(Chapter.class));
    }

    /**
     * Test delete chapter.
     */
    @Test
    void testDeleteChapter() {
        Chapter chapter = new Chapter();
        when(chapterRepository.findById(anyString())).thenReturn(Optional.of(chapter));

        chapterService.deleteChapter("1");

        verify(chapterRepository, times(1)).delete(chapter);
    }

    /**
     * Test get chapter.
     */
    @Test
    void testGetChapter() {
        Chapter chapter = new Chapter();
        when(chapterRepository.findById(anyString())).thenReturn(Optional.of(chapter));

        ChapterResponse response = chapterService.getChapter("1");

        assertNotNull(response);
        verify(chapterRepository, times(1)).findById("1");
    }

    /**
     * Test get all chapter.
     */
    @Test
    void testGetAllChapter() {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String storyId = "1";
        Page<Chapter> page = mock(Page.class);
        when(chapterRepository.findAllByStoryId(any(Pageable.class), eq(storyId))).thenReturn(page);

        PageableResponse response = chapterService.getAllChapter(pageNumber, pageSize, storyId);

        assertNotNull(response);
        verify(chapterRepository, times(1)).findAllByStoryId(any(Pageable.class), eq(storyId));
    }

    /**
     * Test hard delete chapter.
     */
    @Test
    void testHardDeleteChapter() {
        Chapter chapter = new Chapter();
        when(chapterRepository.findById(anyString())).thenReturn(Optional.of(chapter));

        chapterService.hardDeleteChapter("1");

        verify(chapterRepository, times(1)).delete(chapter);
    }
}
