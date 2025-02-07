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

    @GetMapping("/{handle}")
    public ResponseEntity<?> getUser(@PathVariable("handle") String handle) {
        return service.getUserDtoByHandle(handle);
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
    public ResponseEntity<?> postFollow(@PathVariable("shepherd_handle") String shepherdHandle, @RequestParam("sheep_handle") String sheepHandle) {
        return service.postFollow(shepherdHandle, sheepHandle);
    }

    @DeleteMapping("/{shepherd_handle}/delete_follow")
    public ResponseEntity<?> deleteFollow(@PathVariable("shepherd_handle") String shepherdHandle, @RequestParam("sheep_handle") String sheepHandle) {
        return service.deleteFollow(shepherdHandle, sheepHandle);
    }

    // im well aware of how insecure this is, but this is just so its secure from the frontend
    // prob need to generate a token every time we log in, send it to back end and store it. I'll figure that out later
    @PatchMapping("/{handle}/settings")
    public ResponseEntity<?> patchUser(@PathVariable("handle") String handle, @RequestBody AccountFormData formData) {
        return service.patchUser(handle, formData);
    }

    // later, send a token to the backend, if it matches the targHandle token, then we are authenticated and authorized
    @DeleteMapping("/{handle}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("handle") String handle) {
        return service.deleteUser(handle);
    }


    // del acc
}