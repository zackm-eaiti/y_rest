package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.Account;
import y_rest.models.entity.Tweet;
import y_rest.models.entity.TweetLike;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TweetLikeRepository extends JpaRepository<TweetLike, UUID> {
    Optional<TweetLike> findByAccountAndTweet(Account a, Tweet t);
    boolean existsByAccountAndTweet(Account a, Tweet t);
}
