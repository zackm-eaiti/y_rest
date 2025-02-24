package y_rest.models.dto.account;

import org.springframework.web.multipart.MultipartFile;

// these are the mutable fields for an account
public record AccountFormData(
        String handle,
        String displayName,
        String email,
        String password,
        MultipartFile profilePic,
        MultipartFile bannerPic,
        String bio
) {
}
