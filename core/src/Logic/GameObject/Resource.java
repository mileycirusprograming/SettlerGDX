package Logic.GameObject;

/**
 * Created by landfried on 03.02.17.
 */
public class Resource extends GameObject {
    private ResourceType type;
    private boolean used;
    private int occupants = 0;
    public boolean picked = false;
    public boolean moved = false;
    private boolean processed = false;

    public Resource(ResourceType type) {
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    public boolean isUsed() {
        return (occupants > 0);
    }

    public void claim() {
        occupants++;
    }

    public void disclaim() {
        occupants--;
    }

    public void process() {
        processed = true;
    }

    public boolean isProcessed() {
        return processed;
    }
}
