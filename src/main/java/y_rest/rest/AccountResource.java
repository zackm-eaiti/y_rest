package y_rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import y_rest.models.dto.account.AccountFormData;
import y_rest.models.dto.account.AccountPreviewDto;
import y_rest.service.AccountService;

import java.util.List;

/*
I do switch back and forth from account to user a little here -
this is just in the endpoints (and method names) to make the paths look nicer in the urls
 */
@RestController
@RequestMapping("/user")
public class AccountResource {

    @Autowired
    private AccountService service;

    // handle duplicate handles
    // create new user, then go to /user/{handle}
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AccountFormData formData) {
        return service.registerUser(formData);
    }

    @GetMapping("/{handle}")
    public ResponseEntity<?> getUser(@PathVariable("handle") String handle) {
        return service.getUserByHandle(handle);
    }

    // for now, search by username and search by handle - what if no query request param? its fine for now but edit this?
    @GetMapping("/search")
    public List<AccountPreviewDto> searchForUser(@RequestParam("query") String query) {
        return service.searchForUser(query);
    }

    // putting this in Account endpoints because I need to use account repo to check if handle exists
    // I have to use a custom query anyway.
    @GetMapping("/{handle}/tweets")
    public ResponseEntity<?> getUserTweets(@PathVariable("handle") String handle) {
        return service.getUserTweets(handle);
    }

    @GetMapping("/{handle}/likes")
    public ResponseEntity<?> getUserLikes(@PathVariable("handle") String handle) {
        return service.getUserLikes(handle);
    }

    @GetMapping("/{handle}/replies")
    public ResponseEntity<?> getUserReplies(@PathVariable("handle") String handle) {
        return service.getUserReplies(handle);
    }

    @PostMapping("/{shepherd_handle}/post_follow")
    public ResponseEntity<?> postFollow(@PathVariable("shepherd_handle") String shepherd_handle, @RequestParam("sheep_id") String sheep_id) {
        return service.postFollow(shepherd_handle, sheep_id);
    }

    // im well aware of how insecure this is, but this is just so its secure from the frontend
    // prob need to generate a token every time we log in, send it to back end and store it. Ill figure that out later
    @PatchMapping("/{targHandle}/settings")
    public ResponseEntity<?> patchUser(
            @RequestParam("currHandle") String currHandle,
            @PathVariable("targHandle") String targHandle,
            @RequestBody AccountFormData formData) {
        return service.patchUser(currHandle, targHandle, formData);
    }

    // del acc
}
