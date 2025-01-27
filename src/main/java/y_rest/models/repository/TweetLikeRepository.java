package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.TweetLike;
import y_rest.models.entity.AccountTweetCompositeKey;

@Repository
public interface TweetLikeRepository extends JpaRepository<TweetLike, AccountTweetCompositeKey> {
}
