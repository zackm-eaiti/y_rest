package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.HashTag;
import y_rest.models.entity.HashTagId;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, HashTagId> {
}
