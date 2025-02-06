package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.Hashtag;

import java.util.List;
import java.util.UUID;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, UUID> {

    List<Hashtag> findByLabel(String label);
}
