package y_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y_rest.models.entity.Account;
import y_rest.models.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public List<Account> listUsers() {
        return repo.findAll();
    }
}
