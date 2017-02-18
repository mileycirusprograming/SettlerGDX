package Logic.GameObject;

import java.awt.geom.Point2D;

/**
 * Created by landfried on 30.01.17.
 */
public abstract class GameObject {
    private ObjectPosition position;
    private int nationId;

    public GameObject() {
        position = new ObjectPosition();
    }

    public ObjectPosition getPosition() {
        return position;
    }

    public void setPosition(ObjectPosition position) {
        this.position = position;
    }

    public int getNationId() {
        return nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

}
