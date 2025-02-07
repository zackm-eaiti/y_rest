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


    /*
     * How we are handling login in is through:
     *       ensuring form data and password are correct
     *       if so, generate a token, store in account entity.
     *       then, send that token to the frontend
     *       use that token when sending requests to determine
     *       authorization level when viewing content.
     *
     * What is the best way to do this? Through sessions?
     *       This would entail sending that session stuff
     *       over and then storing it in the user. We can
     *       then just leave that key in the account entity,
     *       we don't need to delete it, just rewrite every
     *       time we log in with the new session information.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountFormData formData) {
         return service.login(formData);
    }

}
