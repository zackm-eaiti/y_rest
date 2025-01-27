package y_rest.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.IdClass;
import y_rest.models.dto.FollowDto;

import java.time.Instant;

import java.util.UUID;

@Entity
@Table
@IdClass(FollowId.class)
public class Follow {

    @Id
    @Column(name = "sheep_id")
    private UUID sheepId;

    @Id
    @Column(name = "shepherd_id")
    private UUID shepherdId;

    @Column
    private Instant created;

    public Follow(UUID sheepId, UUID shepherdId, Instant created) {
        this.sheepId = sheepId;
        this.shepherdId = shepherdId;
        this.created = created;
    }

    public UUID getSheepId() {
        return sheepId;
    }

    public void setSheepId(UUID sheepId) {
        this.sheepId = sheepId;
    }

    public UUID getShepherdId() {
        return shepherdId;
    }

    public void setShepherdId(UUID shepherdId) {
        this.shepherdId = shepherdId;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public FollowDto convertToDto() {
        return new FollowDto(
                getSheepId(),
                getShepherdId(),
                getCreated()
        );
    }
}