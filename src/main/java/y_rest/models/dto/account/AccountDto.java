package y_rest.models.dto.account;

import y_rest.models.dto.tweet.TweetPreviewDto;
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
        String phone,
        String profilePicUrl,
        String bannerPicUrl,
        String bio,
        List<AccountPreviewDto> following,
        List<AccountPreviewDto> followers,
        List<TweetPreviewDto> likes
) {
    public static AccountDto fromAccount(Account account) {
        return new AccountDto(
                account.getId(),
                account.getCreated(),
                account.getHandle(),
                account.getDisplayName(),
                account.getEmail(),
                account.getPhone(),
                account.getProfilePicUrl(),
                account.getBannerPicUrl(),
                account.getBio(),
                account.getFollowing().stream().map(AccountPreviewDto::fromAccount).toList(),
                account.getFollowers().stream().map(AccountPreviewDto::fromAccount).toList(),
                account.getLikes().stream().map(TweetPreviewDto::fromTweet).toList()
        );
    }
}
