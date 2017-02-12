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

    private MissionCarrier getMissionCarrier() {
        return (MissionCarrier)getMission();
    }

    @Override
    public void update() {
        //if (getMissionCarrier() != null)
        //    getMissionCarrier().finish();

    }

    // sollten nciht sowieso nur SettlerCarrier MissionCarriers bekommen und für alles andere genau so?
    @Override
    public boolean isCorrectMission(Mission mission) {
        return (mission instanceof MissionCarrier);
    }
}
