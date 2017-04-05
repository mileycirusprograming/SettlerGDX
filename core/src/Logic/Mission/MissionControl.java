package Logic.Mission;

import Logic.Break;
import Logic.GameObject.*;
import Logic.NationObjectAccessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by landfried on 30.01.17.
 */
public class MissionControl {
    private List<Mission> unassignedMissions;
    private List<Mission> assignedMissions;
    private int nationId;
    private NationObjectAccessor objectAccessor;

    public MissionControl(int nationId, NationObjectAccessor objectAccessor) {
        unassignedMissions = new ArrayList<>();
        assignedMissions = new ArrayList<>();
        this.nationId = nationId;
        this.objectAccessor = objectAccessor;
    }

    public void prepareMissions() {
        for (Mission mission : unassignedMissions)
            mission.abort();
        unassignedMissions.clear();

        for (Building building : objectAccessor.getBuildings()) {
            for (ResourceType neededRessource : building.getNeededResources().keySet()) {
                if (getFreeResource(neededRessource) == null)
                    continue;

                for (int i = 0; i < building.getNeededResources().get(neededRessource); i++) {
                    Resource freeResource = getFreeResource(neededRessource);
                    if (freeResource == null)
                        break;
                    unassignedMissions.add(new MissionCarrier(building, freeResource));

                }
            }

            if (building.waitingForContruction()) {
                unassignedMissions.add(new MissionBuilder(building));
            }
        }

    }



    private Resource getFreeResource(ResourceType type) {
        for (Resource resource : objectAccessor.getResources())
            if (resource.getType().equals(type) && !resource.isUsed())
                return resource;
        return null;
    }


    private SettlerCarrier getFreeCarrier() {

        for (Settler settler : objectAccessor.getSettlers())
            if (settler instanceof SettlerCarrier)
                if (!settler.isBusy())
                    return (SettlerCarrier)settler;
        return null;
    }

    private Settler getFreeSettler(Class<? extends Settler> settlerType) {
        for (Settler settler : objectAccessor.getSettlers())
            if (settlerType.isInstance(settler))
                if (!settler.isBusy())
                    return settler;
        return null;
    }

    public void distributeMissions() {
        for (Mission mission : unassignedMissions) {
            if (mission instanceof MissionCarrier) {
                //SettlerCarrier freeCarrier = getFreeCarrier();
                SettlerCarrier freeCarrier = (SettlerCarrier)getFreeSettler(SettlerCarrier.class);
                if (freeCarrier != null) {
                    freeCarrier.setMission(mission);
                    assignedMissions.add(mission);
                }
            }

            if (mission instanceof MissionBuilder) {
                SettlerBuilder freeBuilder = (SettlerBuilder)getFreeSettler(SettlerBuilder.class);
                if (freeBuilder != null) {
                    freeBuilder.setMission(mission);
                    assignedMissions.add(mission);                }
            }
        }
        unassignedMissions.removeAll(assignedMissions);
    }

    public void removeTerminatedMissions() {
        assignedMissions.removeIf(mission -> mission.getState() == MissionState.COMPLETE || mission.getState() == MissionState.FAIL);
        /*
        for (Iterator<Mission> iterator = assignedMissions.iterator(); iterator.hasNext();) {
            Mission mission = iterator.next();
            if (mission.getState() == MissionState.COMPLETE || mission.getState() == MissionState.FAIL)
                iterator.remove();
        }
        */
    }

}
