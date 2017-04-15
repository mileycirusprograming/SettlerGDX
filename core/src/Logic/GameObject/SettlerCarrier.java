package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionCarrier;

/**
 * Created by landfried on 30.01.17.
 */
public class SettlerCarrier extends Settler {
    private Resource carriedResource;

    public SettlerCarrier() {
        super();
    }

    public SettlerCarrier(ObjectPosition position) {
        super(position);
    }

    @Override
    public void handleMovement() {
        if (carriedResource != null)
            carriedResource.setPosition(getPosition());
    }

    @Override
    protected void updateDestination() {
        if (getMission() == null)
            return;

        switch (state) {
            case WAITING:
                break;
            case DEST_RESOURCE:
                setDestination(getMissionCarrier().getResource().getPosition());
                break;
            case REACHED_RESOURCE:
                break;
            case DEST_BUILDING:
                setDestination(getMissionCarrier().getBuilding().getPosition());
                break;
            case REACHED_BUILDING:
                break;
        }
    }

    @Override
    protected void updateState() {
        if (getMission() == null)
            return;

        switch (state) {
            case WAITING:
                if (getMission() != null)
                    state = State.DEST_RESOURCE;
                break;
            case DEST_RESOURCE:
                if (getDestination().equals(getPosition()))
                    state = State.REACHED_RESOURCE;
                break;
            case REACHED_RESOURCE:
                pickResource(getMissionCarrier().getResource());
                if (getMissionCarrier().getResource().picked)
                    state = State.DEST_BUILDING;
                break;
            case DEST_BUILDING:
                if (getDestination().equals(getPosition()))
                    state = State.REACHED_BUILDING;
                break;
            case REACHED_BUILDING:
                dropResource();
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

    private void pickResource(Resource resource) {
        if (getPosition().equals(resource.getPosition())) {
            resource.picked = true;
            carriedResource = resource;
        }
    }

    private void dropResource() {
        carriedResource.picked = false;
        carriedResource = null;
    }
}
