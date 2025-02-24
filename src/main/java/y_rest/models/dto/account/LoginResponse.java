package y_rest.models.dto.account;

import java.util.UUID;

public record LoginResponse(
        UUID id,
        UUID token,
        String handle
) {
}