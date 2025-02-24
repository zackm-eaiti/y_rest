package y_rest.models.dto.account;

import y_rest.models.entity.Account;

import java.util.Base64;
import java.util.UUID;

// used when we are viewing accounts in lists,
// and to avoid circular references in follows
public record AccountPreviewDto(
        UUID id,
        String handle,
        String displayName,
        String bio
) {
    public static AccountPreviewDto fromAccount(Account account) {
        return new AccountPreviewDto(
                account.getId(),
                account.getHandle(),
                account.getDisplayName(),
                account.getBio());
    }
}
