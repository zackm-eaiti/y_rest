package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.Account;
import y_rest.models.entity.Tweet;

import java.util.List;
import java.util.UUID;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, UUID> {

    @Query("(SELECT t FROM Tweet t WHERE t.textContent LIKE %:phrase%)")
    List<Tweet> findByPhraseLike(@Param("phrase") String phrase);

    @Query("SELECT t FROM Tweet t where t.parentTweet.id = :id")
    List<Tweet> findRepliesByParentId(UUID id);
}