package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.AccountTweetCompositeKey;
import y_rest.models.entity.Bookmark;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, AccountTweetCompositeKey> {

}
