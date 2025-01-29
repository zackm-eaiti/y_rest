package y_rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y_rest.models.entity.Account;
import y_rest.models.repository.AccountRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public List<Account> listAccounts() {
        return repo.findAll();
    }

    public Account getAccountById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    // make case insensitive
    public Account getAccountByHandle(String handle) {
        return repo.findByHandle(handle).orElse(null);
    }
}
