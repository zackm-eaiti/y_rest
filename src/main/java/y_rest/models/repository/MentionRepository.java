package y_rest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y_rest.models.entity.Mention;

import java.util.List;
import java.util.UUID;

@Repository
public interface MentionRepository extends JpaRepository<Mention, UUID> {

    List<Mention> findByLabel(String label);
}
