package Logic.GameObject;

import java.awt.geom.Point2D;

/**
 * Created by landfried on 30.01.17.
 */
public abstract class GameObject {
    private Point2D position;
    private int nationId;


    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public int getNationId() {
        return nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

}
