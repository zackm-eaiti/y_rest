package y_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y_rest.models.entity.Follow;
import y_rest.models.repository.FollowRepository;


import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowRepository repo;

    public List<Follow> listFollows() {
        return repo.findAll();
    }
}
