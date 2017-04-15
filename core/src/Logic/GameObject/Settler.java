package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Unit.MovementUnit;

import java.util.EventListener;

/**
 * Created by landfried on 30.01.17.
 */
public abstract class Settler extends GameObject {
    private boolean busy = false;
    private Mission mission;
    protected enum State {UNDEFINED, WAITING, DONE, DODGE, DEST_RESOURCE, REACHED_RESOURCE, DEST_BUILDING, REACHED_BUILDING, BUILD}
    protected State state = State.WAITING;
    private State childState = State.UNDEFINED;
    protected MovementUnit movementUnit = new MovementUnit();
    private long dodgeReturn = System.currentTimeMillis();

    public Settler() {
        super();
        setPosition(super.getPosition());
        movementUnit.setDestination(getPosition());
        init();
    }

    public Settler(ObjectPosition position) {
        super();
        setPosition(position);
        setDestination(getPosition());
        init();
    }

    private void init() {
        movementUnit.setMoveDelayTime(150);
        movementUnit.setEventListener(new Logic.Unit.EventListener() {
            @Override
            public void handle() {
                handleMovement();
            }
        });
    }

    public void handleMovement() {

    }

    public ObjectPosition getDirection() {
        return movementUnit.getDirection();
    }

    @Override
    public ObjectPosition getPosition() {
        return movementUnit.getPosition();
    }

    @Override
    public void setPosition(ObjectPosition position) {
        movementUnit.setPosition(position);
    }

    public ObjectPosition getDestination() {
        return movementUnit.getDestination();
    }

    public void setDestination(ObjectPosition destination) {
        movementUnit.setDestination(destination);
    }

    protected abstract void updateState();

    protected abstract void updateDestination();

    private void parentUpdateState() {


        switch (state) {
            case DODGE:
                if (getPosition().equals(getDestination()) && System.currentTimeMillis() > dodgeReturn)
                    continueMission();
                break;

            default:
                if (movementUnit.getPositionClaimed() > 0) {
                    childState = state;
                    state = State.DODGE;
                    dodgeReturn = System.currentTimeMillis() + 300;
                }
                break;
        }

    }

    private void parentUpdateDestination() {
        switch (state) {
            case DODGE:
                setDestination(getPosition().getRandomNeighbour());
                break;
        }
    }

    public void update(long currentTime) {
        parentUpdateState();
        parentUpdateDestination();
        updateState();
        updateDestination();
        movementUnit.move(currentTime);

    }
    protected abstract boolean isCorrectMission(Mission mission);
    protected abstract void initMission();

    protected void finishMission() {
        mission.finish();
        mission = null;
    }

    protected void continueMission() {
        state = childState;
        updateDestination();
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public boolean setMission(Mission mission) {
        if (!isCorrectMission(mission)) {
            mission.abort();
            return false;
        }

        if (this.mission != null)
            this.mission.abort();

        this.mission = mission;
        setBusy(true);
        this.mission.begin();
        initMission();

        return true;
    }

    public Mission getMission() {
        return mission;
    }

}
