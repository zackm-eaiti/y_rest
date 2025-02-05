package y_rest.models.dto.account;
import y_rest.models.entity.Account;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

// used when clicking on someone's profile
public record AccountDto(
        UUID id,
        Instant created,
        String handle,
        String displayName,
        String email,
        String profilePicUrl,
        String bannerPicUrl,
        String bio,
        List<AccountPreviewDto> following, // preview essentially forces max recursive depth of 1 on entity loading
        List<AccountPreviewDto> followers
) {
    public static AccountDto fromAccount(Account account) {
        return new AccountDto(
                account.getId(),
                account.getCreated(),
                account.getHandle(),
                account.getDisplayName(),
                account.getEmail(),
                account.getProfilePicUrl(),
                account.getBannerPicUrl(),
                account.getBio(),
                account.getShepherds().stream().map(AccountPreviewDto::fromAccount).toList(),
                account.getSheep().stream().map(AccountPreviewDto::fromAccount).toList()
        );
    }
}
