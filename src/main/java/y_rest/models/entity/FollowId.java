package y_rest.models.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FollowId implements Serializable {

    private UUID sheepId;
    private UUID shepherdId;

//    why is this necessary?
    public FollowId() {}

    public FollowId(UUID sheepId, UUID shepherdId) {
        this.sheepId = sheepId;
        this.shepherdId = shepherdId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FollowId followId = (FollowId) o;
        return Objects.equals(sheepId, followId.sheepId) && Objects.equals(shepherdId, followId.shepherdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sheepId, shepherdId);
    }
}