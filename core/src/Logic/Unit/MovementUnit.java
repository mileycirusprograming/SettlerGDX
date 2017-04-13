package Logic.Unit;

import Logic.GameObject.ObjectPosition;

import java.util.ArrayList;

/**
 * Created by landfried on 13.04.17.
 */
public class MovementUnit {
    private ObjectPosition position;
    private ObjectPosition destination;
    private ObjectPosition direction;
    private static ArrayList<MovementUnit> movementUnits = new ArrayList<>();
    private int movementBlocked;
    private int positionClaimed;
    private long moveDelayTime;
    private long lastMoveTime;

    public MovementUnit() {
        position = new ObjectPosition();
        destination = new ObjectPosition();
        direction = new ObjectPosition();
        movementUnits.add(this);
        movementBlocked = 0;
        positionClaimed = 0;
        moveDelayTime = Long.MAX_VALUE;
        lastMoveTime = System.currentTimeMillis();
    }

    private static boolean isPositionOccupied(ObjectPosition position) {
        for (MovementUnit movementUnit : movementUnits)
            if (movementUnit.getPosition().equals(position)) {
                movementUnit.claimPosition();
                return true;
            }
        return false;
    }

    public void move() {
        ObjectPosition newPosition = new ObjectPosition(position);

        if (direction.x != 0)
            newPosition.x += direction.x;
        else
            newPosition.y += direction.y;

        if (isPositionOccupied(newPosition))
            movementBlocked++;
        else
            setPosition(newPosition);

    }

    public void move(long currentTime) {
        for (; lastMoveTime + moveDelayTime <= currentTime; lastMoveTime += moveDelayTime)
            move();
    }

    protected void claimPosition() {
        positionClaimed++;
    }

    private void calculateDirection() {
        int deltaX = destination.x - position.x;
        int deltaY = destination.y - position.y;

        direction.x = Integer.signum(deltaX);
        direction.y = Integer.signum(deltaY);
    }

    public ObjectPosition getPosition() {
        return new ObjectPosition(position);
    }

    public void setPosition(ObjectPosition position) {
        this.position = position;
        calculateDirection();
        movementBlocked = 0;
        positionClaimed = 0;
    }

    public ObjectPosition getDestination() {
        return new ObjectPosition(destination);
    }

    public void setDestination(ObjectPosition destination) {
        this.destination = destination;
        calculateDirection();
    }

    public ObjectPosition getDirection() {
        return new ObjectPosition(direction);
    }

    public int getMovementBlocked() {
        return movementBlocked;
    }

    public int getPositionClaimed() {
        return positionClaimed;
    }

}
