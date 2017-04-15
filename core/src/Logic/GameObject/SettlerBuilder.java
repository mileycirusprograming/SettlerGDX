package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionBuilder;

/**
 * Created by landfried on 30.01.17.
 */
public class SettlerBuilder extends Settler {
    //private final ObjectPosition direction = new ObjectPosition();
    private boolean resourceReached;
    private  boolean buildingReached;
    public SettlerBuilder() {
        super();
    }

    public SettlerBuilder(ObjectPosition position) {
        super(position);
    }

    @Override
    protected void updateDestination() {
        if (getMission() == null)
            return;

        switch (state) {
            case WAITING:
                break;
            case DEST_BUILDING:
                setDestination(getMissionBuilder().getBuilding().getPosition());
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
                    state = State.DEST_BUILDING;
                break;
            case DEST_BUILDING:
                if (getDestination().equals(getPosition()))
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
    public boolean isCorrectMission(Mission mission) {
        return (mission instanceof MissionBuilder);
    }

    @Override
    protected void initMission() {
        getMissionBuilder().getBuilding().builders++;
        state = State.DEST_BUILDING;
        updateDestination();
    }

    @Override
    protected void finishMission() {
        getMissionBuilder().getBuilding().builders--;
        super.finishMission();
    }

    @Override
    protected void continueMission() {
        state = State.DEST_BUILDING;
        updateDestination();
    }
}