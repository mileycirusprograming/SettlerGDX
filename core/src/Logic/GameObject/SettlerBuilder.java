package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionBuilder;

/**
 * Created by landfried on 30.01.17.
 */
public class SettlerBuilder extends Settler {
    private ObjectPosition destination;
    private final ObjectPosition direction = new ObjectPosition();
    private boolean resourceReached;
    private  boolean buildingReached;
    private enum State {WAITING, DEST_BUILDING, REACHED_BUILDING, BUILD, DONE}
    private State state;
    public SettlerBuilder() {
        super();
    }

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

    // sollten nciht sowieso nur SettlerCarrier MissionCarriers bekommen und für alles andere genau so?
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