package Logic.GameObject;

import View.Analyzeable;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by landfried on 30.01.17.
 */
public abstract class GameObject implements Analyzeable {
    private ObjectPosition position;
    private int nationId;

    public GameObject() {
        position = new ObjectPosition();
    }

    public HashMap<String, Serializable> getProperties() {
        HashMap<String, Serializable> properties = new HashMap<>();
        properties.put("position", position);
        properties.put("nationId", nationId);
        return  properties;
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
