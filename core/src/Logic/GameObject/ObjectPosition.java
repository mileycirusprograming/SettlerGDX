package Logic.GameObject;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Gustav on 18.02.2017.
 */
public class ObjectPosition implements Serializable {
    public int x;
    public int y;
    private static Random random = new Random();


    public ObjectPosition() {

    }

    public boolean equals(ObjectPosition objectPosition) {
        return (x == objectPosition.x && y == objectPosition.y);
    }

    public ObjectPosition(ObjectPosition objectPosition) {
        this.x = objectPosition.x;
        this.y = objectPosition.y;
    }

    public ObjectPosition getRandomNeighbour() {
        ObjectPosition randomNeighbour = new ObjectPosition(this);
        int dir = random.nextBoolean() ? 1 : -1;
        if (random.nextBoolean())
            randomNeighbour.x += dir;
        else
            randomNeighbour.y += dir;
        return randomNeighbour;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
