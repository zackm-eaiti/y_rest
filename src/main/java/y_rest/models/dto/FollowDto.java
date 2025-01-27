package y_rest.models.dto;

import java.time.Instant;
import java.util.UUID;

public class FollowDto {
    private final UUID sheep_id;
    private final UUID shepherd_id;
    private final Instant created;

    public FollowDto(UUID sheep_id, UUID shepherd_id, Instant created) {
        this.sheep_id = sheep_id;
        this.shepherd_id = shepherd_id;
        this.created = created;
    }

    public UUID getSheep_id() {
        return sheep_id;
    }

    public UUID getShepherd_id() {
        return shepherd_id;
    }

    public Instant getCreated() {
        return created;
    }
}
