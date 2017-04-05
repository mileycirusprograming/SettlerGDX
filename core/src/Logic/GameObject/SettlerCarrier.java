package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionCarrier;

/**
 * Created by landfried on 30.01.17.
 */
public class SettlerCarrier extends Settler {
    private boolean resourceReached;
    private  boolean buildingReached;
    private enum State {WAITING, DEST_RESOURCE, REACHED_RESOURCE, DEST_BUILDING, REACHED_BUILDING, DONE}
    private State state;
    public SettlerCarrier() {
        super();
    }

    private void updateDestination() {
        destination = new ObjectPosition(getPosition());
        if (getMission() == null)
            return;

        switch (state) {
            case WAITING:
                break;
            case DEST_RESOURCE:
                destination = getMissionCarrier().getResource().getPosition();
                break;
            case REACHED_RESOURCE:
                break;
            case DEST_BUILDING:
                destination = getMissionCarrier().getBuilding().getPosition();
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
                    state = State.DEST_RESOURCE;
                break;
            case DEST_RESOURCE:
                if (destination.equals(getPosition()))
                    state = State.REACHED_RESOURCE;
                break;
            case REACHED_RESOURCE:
                if (getMissionCarrier().getResource().picked)
                    state = State.DEST_BUILDING;
                break;
            case DEST_BUILDING:
                if (destination.equals(getPosition()))
                    state = State.REACHED_BUILDING;
                break;
            case REACHED_BUILDING:
                if (!getMissionCarrier().getResource().picked)
                    state = State.DONE;
                break;
            case DONE:
                finishMission();
                setBusy(false);
                state = State.WAITING;
        }

    }

    private MissionCarrier getMissionCarrier() {
        return (MissionCarrier) getMission();
    }

    @Override
    public void update() {
        updateState();
        updateDestination();
        if (state == State.DEST_BUILDING)
            getMissionCarrier().getResource().setPosition(new ObjectPosition(getPosition()));
        if (state == State.REACHED_RESOURCE)
            getMissionCarrier().getResource().picked = true;
        if (state == State.REACHED_BUILDING) {
            getMissionCarrier().getResource().picked = false;
            getMissionCarrier().getResource().setPosition(new ObjectPosition(getPosition()));

        }
        updateDirection();
    }

    // sollten nciht sowieso nur SettlerCarrier MissionCarriers bekommen und für alles andere genau so?
    @Override
    public boolean isCorrectMission(Mission mission) {
        return (mission instanceof MissionCarrier);
    }


    @Override
    protected void initMission() {
        state = State.DEST_RESOURCE;
        updateDestination();
    }
}