package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import y_rest.models.dto.account.AccountFormData;
import y_rest.service.AccountService;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {

    @Autowired
    private AccountService service;

    // todo: handle duplicate handles
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AccountFormData formData) {
        return service.registerUser(formData);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountFormData formData) {
         return service.login(formData);
    }

}
