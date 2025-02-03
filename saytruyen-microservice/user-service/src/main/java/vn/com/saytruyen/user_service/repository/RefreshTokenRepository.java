package vn.com.saytruyen.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.saytruyen.user_service.model.RefreshToken;
import vn.com.saytruyen.user_service.model.User;

import java.util.Optional;

/**
 * The interface Refresh token repository.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * Find by token optional.
     *
     * @param token the token
     * @return the optional
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Delete all by user.
     *
     * @param user the user
     */
    @Modifying
    @Query("delete from RefreshToken rt where rt.user = ?1")
    void deleteAllByUser(User user);
}