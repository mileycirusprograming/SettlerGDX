package Logic;

import Logic.GameObject.Building;
import Logic.GameObject.Resource;
import Logic.Mission.Mission;
import Logic.Mission.MissionControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by landfried on 30.01.17.
 */
public class Nation {
    private static int nationCount;
    private MissionControl missionControl;
    public final int id;

    private NationObjectAccessor objectAccessor;

    public Nation(int id, NationObjectAccessor objectAccessor) {
        this.id = id;
        missionControl = new MissionControl(id, objectAccessor);
        this.objectAccessor = objectAccessor;
    }

    public static int getNextId() {
        return nationCount++;
    }


    public void update() {
        missionControl.prepareMissions();
        missionControl.distributeMissions();

        System.out.println(objectAccessor.getBuildings());
        System.out.println(objectAccessor.getSettlers());
        System.out.println(missionControl.getMissions());


    }



    public List<Mission> getMissions() {
        return missionControl.getMissions();
    }
}
