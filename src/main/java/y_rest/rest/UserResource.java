package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import y_rest.models.dto.UserDto;
import y_rest.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDto> getUsers() {
        return service.listUsers().stream().map(UserDto::new).toList();
    }
}
