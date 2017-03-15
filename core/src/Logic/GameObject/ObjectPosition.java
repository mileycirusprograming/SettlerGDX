package Logic.GameObject;

/**
 * Created by Gustav on 18.02.2017.
 */
public class ObjectPosition {
    public int x;
    public int y;

    public ObjectPosition() {

    }

    public boolean equals(ObjectPosition objectPosition) {
        if (x == objectPosition.x && y == objectPosition.y)
            return true;
        return false;
    }

    public ObjectPosition(ObjectPosition objectPosition) {
        this.x = objectPosition.x;
        this.y = objectPosition.y;
    }
}
