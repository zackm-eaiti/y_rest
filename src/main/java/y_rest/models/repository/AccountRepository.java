package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.Account;
import y_rest.models.entity.Tweet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>  {
    Optional<Account> findByHandle(String handle);

    boolean existsByHandle(String handle);
    boolean existsByEmail(String email);

    @Query("SELECT a FROM Account a WHERE a.displayName LIKE %:query% OR a.handle LIKE %:query%")
    List<Account> findByDisplayNameOrHandleLike(@Param("query") String query);

    @Query("SELECT t FROM Tweet t WHERE t.account.handle LIKE %:handle%")
    List<Tweet> findTweetByAccountHandle(@Param("handle") String handle);

    @Query("SELECT tl.tweet FROM TweetLike tl WHERE tl.account.handle = :handle")
    List<Tweet> findLikedTweetsByAccountHandle(@Param("handle") String handle);

    @Query("SELECT t FROM Tweet t where (t.parentTweet IS NOT NULL or t.retweet IS NOT NULL) and t.account.handle = :handle ")
    List<Tweet> findRepliesByAccountHandle(@Param("handle") String handle);
}
