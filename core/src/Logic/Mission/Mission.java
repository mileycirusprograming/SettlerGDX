package Logic.Mission;

import Logic.GameObject.Building;

/**
 * Created by landfried on 30.01.17.
 */
public abstract class Mission {
    private MissionState state;
    private Building building;

    public Mission(Building building) {
        state = MissionState.ASSIGN;
        this.building = building;
    }

    public void begin() {
        setState(MissionState.WORK);
    }

    public void abort() {
        setState(MissionState.FAIL);
    }

    public void finish() {
        setState(MissionState.COMPLETE);
    }

    public MissionState getState() {
        return state;
    }

    private void setState(MissionState state) {
        this.state = state;
        building.missionStateChanged(this);
    }
}
