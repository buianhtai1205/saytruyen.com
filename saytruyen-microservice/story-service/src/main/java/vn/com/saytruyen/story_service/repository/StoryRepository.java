package vn.com.saytruyen.story_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.story_service.model.Story;

/**
 * The interface Story repository.
 */
@Repository
public interface StoryRepository extends MongoRepository<Story, String> {
}
