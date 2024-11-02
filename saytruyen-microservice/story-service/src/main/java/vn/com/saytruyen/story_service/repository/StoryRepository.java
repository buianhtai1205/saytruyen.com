package vn.com.saytruyen.story_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.story_service.model.Story;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface Story repository.
 */
@Repository
public interface StoryRepository extends MongoRepository<Story, String> {

    /**
     * Find by now created at list.
     *
     * @param currentDateTime the current date time
     * @return the list
     */
    @Query("{ 'createdAt' : { $gte: ?0 } }")
    List<Story> findByNowCreatedAt(LocalDateTime currentDateTime);
}
