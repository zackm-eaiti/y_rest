package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import y_rest.models.dto.FollowDto;
import y_rest.models.entity.Follow;
import y_rest.service.FollowService;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowResource {

    @Autowired
    private FollowService service;

    @GetMapping
    public List<FollowDto> getUsers() {
        return service.listFollows().stream()
                .map(Follow::convertToDto)
                .toList();
    }
}
