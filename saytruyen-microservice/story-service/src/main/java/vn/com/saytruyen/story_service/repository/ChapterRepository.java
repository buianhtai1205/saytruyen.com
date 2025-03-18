package vn.com.saytruyen.story_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.story_service.model.Chapter;

import java.util.List;

/**
 * The interface Chapter repository.
 */
@Repository
public interface ChapterRepository extends MongoRepository<Chapter, String> {

    /**
     * Find all by story id page.
     *
     * @param pageable the pageable
     * @param storyId  the story id
     * @return the page
     */
    @Query("{ 'storyId': ?0 }")
    Page<Chapter> findAllByStoryId(Pageable pageable, String storyId);

    /**
     * Gets list chapter simple by story id.
     *
     * @param storyId the story id
     * @return the list chapter simple by story id
     */
    @Query(value = "{ 'storyId': ?0 }", fields = "{ 'id': 1, 'name': 1, 'createdAt': 1 }")
    List<Chapter> getListChapterSimpleByStoryId(String storyId);
}
