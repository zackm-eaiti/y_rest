package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import y_rest.models.dto.account.AccountDto;
import y_rest.models.entity.Account;
import y_rest.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
public class AccountResource {

    @Autowired
    private AccountService service;

    @GetMapping("/all")
    public List<AccountDto> getUsers() {
        return service.listAccounts().stream()
                .map(AccountDto::fromAccount)
                .toList();
    }

    // user logged in, user logged out
    @GetMapping("/{handle}")
    public AccountDto getUser(@PathVariable("handle") String handle) {
        Account account = service.getAccountByHandle(handle.toLowerCase());
        return AccountDto.fromAccount(account);
    }



}
