package Logic.GameObject;

import java.io.Serializable;

/**
 * Created by Gustav on 18.02.2017.
 */
public class ObjectPosition implements Serializable {
    public int x;
    public int y;

    public ObjectPosition() {

    }

    public boolean equals(ObjectPosition objectPosition) {
        return (x == objectPosition.x && y == objectPosition.y);
    }

    public ObjectPosition(ObjectPosition objectPosition) {
        this.x = objectPosition.x;
        this.y = objectPosition.y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
