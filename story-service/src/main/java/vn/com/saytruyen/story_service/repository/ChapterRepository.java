package vn.com.saytruyen.story_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.story_service.model.Chapter;

/**
 * The interface Chapter repository.
 */
@Repository
public interface ChapterRepository extends MongoRepository<Chapter, String> {
}
