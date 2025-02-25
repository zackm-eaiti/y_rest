package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import y_rest.service.AccountService;


@RestController()
@RequestMapping("/i/want/to/nuke")
public class Nuke {

    @Autowired
    private AccountService service;

    // just for funsies
    @DeleteMapping
    public void nuke() {
        service.nuke();
    }
}


