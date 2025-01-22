package y_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y_rest.models.entity.User;
import y_rest.models.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listUsers() {
        return repo.findAll();
    }
}
