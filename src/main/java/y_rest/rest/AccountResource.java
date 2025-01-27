package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import y_rest.models.dto.AccountDto;
import y_rest.models.entity.Account;
import y_rest.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountResource {

    @Autowired
    private AccountService service;

    @GetMapping
    public List<AccountDto> getUsers() {
        return service.listUsers().stream()
                .map(Account::convertToDto)
                .toList();
    }
}
