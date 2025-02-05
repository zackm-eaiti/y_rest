package y_rest.models.dto.account;

// these are the mutable fields for an account
public record AccountFormData(
        String handle,
        String displayName,
        String email,
        String password,
        String profilePicUrl,
        String bannerPicUrl,
        String bio
) {
}
