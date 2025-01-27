package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.Follow;
import y_rest.models.entity.FollowId;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId>  {
}
