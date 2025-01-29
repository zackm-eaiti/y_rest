package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.Account;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>  {
}
