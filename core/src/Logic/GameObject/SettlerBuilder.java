package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionBuilder;

/**
 * Created by landfried on 30.01.17.
 */
public class SettlerBuilder extends Settler {
    //private final ObjectPosition direction = new ObjectPosition();
    //können hier und bei Carrier die booleans nicht mal raus?
    private boolean resourceReached;
    private  boolean buildingReached;
    private enum State {WAITING, DEST_BUILDING, REACHED_BUILDING, BUILD, DONE}
    private State state;
    public SettlerBuilder() {
        super();
    }

    //warum wird denn jedes mal die relativ statische destination neu gesetzt?
    private void updateDestination() {
        destination = new ObjectPosition(getPosition());
        if (getMission() == null)
            return;

        switch (state) {
            case WAITING:
                break;
            case DEST_BUILDING:
                destination = getMissionBuilder().getBuilding().getPosition();
                break;
            case REACHED_BUILDING:
                break;
        }
    }

    private void updateState() {
        if (getMission() == null)
            return;

        switch (state) {
            case WAITING:
                if (getMission() != null)
                    state = State.DEST_BUILDING;
                break;
            case DEST_BUILDING:
                if (destination.equals(getPosition()))
                    state = State.REACHED_BUILDING;
                break;
            case REACHED_BUILDING:
                state = State.BUILD;
                break;
            case BUILD:
                getMissionBuilder().getBuilding().construct();
                if (getMissionBuilder().getBuilding().getState() == BuildingState.BUILT)
                    state = State.DONE;
                break;
            case DONE:
                finishMission();
                setBusy(false);
                state = State.WAITING;
        }

    }

    private MissionBuilder getMissionBuilder() {
        return (MissionBuilder) getMission();
    }

    @Override
    public void update() {
        updateState();
        updateDestination();
        updateDirection();

    }

    @Override
    public boolean isCorrectMission(Mission mission) {
        return (mission instanceof MissionBuilder);
    }

    public ObjectPosition getDirection() {
        return direction;
    }

    @Override
    protected void initMission() {
        state = State.DEST_BUILDING;
        updateDestination();
    }
}