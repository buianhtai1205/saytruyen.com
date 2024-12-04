package vn.com.saytruyen.story_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.story_service.model.Banner;

/**
 * The interface Banner repository.
 */
@Repository
public interface BannerRepository extends MongoRepository<Banner, String> {
}
