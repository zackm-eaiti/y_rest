package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
}
