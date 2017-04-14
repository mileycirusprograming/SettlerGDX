package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionCarrier;

/**
 * Created by landfried on 30.01.17.
 */
public class SettlerCarrier extends Settler {

    public SettlerCarrier() {
        super();
    }

    public SettlerCarrier(ObjectPosition position) {
        super(position);
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
                getMissionCarrier().getResource().picked = true;
                if (getMissionCarrier().getResource().picked)
                    state = State.DEST_BUILDING;
                break;
            case DEST_BUILDING:
                getMissionCarrier().getResource().setPosition(new ObjectPosition(getPosition()));
                if (getDestination().equals(getPosition()))
                    state = State.REACHED_BUILDING;
                break;
            case REACHED_BUILDING:
                getMissionCarrier().getResource().picked = false;
                getMissionCarrier().getResource().setPosition(new ObjectPosition(getPosition()));
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
}