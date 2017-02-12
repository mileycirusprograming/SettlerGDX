package Logic.Mission;

import Logic.GameObject.*;
import Logic.NationObjectAccessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landfried on 30.01.17.
 */
public class MissionControl {
    private List<Mission> missions;
    private int nationId;
    private NationObjectAccessor objectAccessor;

    public MissionControl(int nationId, NationObjectAccessor objectAccessor) {
        missions = new ArrayList<Mission>();
        this.nationId = nationId;
        this.objectAccessor = objectAccessor;
    }

    public void prepareMissions() {
        missions.clear();
        for (Building building : objectAccessor.getBuildings()) {
            for (ResourceType neededRessource : building.getNeededResources().keySet()) {
                if (getFreeResource(neededRessource) == null)
                    continue;

                for (int i = 0; i < building.getNeededResources().get(neededRessource); i++) {
                    Resource freeResource = getFreeResource(neededRessource);
                    if (freeResource == null)
                        break;
                    missions.add(new MissionCarrier(building, freeResource));
                }
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
                if (!((SettlerCarrier)settler).isBusy())
                    return (SettlerCarrier)settler;
        return null;
    }

    public void distributeMissions() {
        for (Mission mission : missions) {
            if (mission instanceof MissionCarrier) {
                SettlerCarrier freeCarrier = getFreeCarrier();
                if (freeCarrier != null)
                    freeCarrier.setMission(mission);
            }
        }
    }




    public List<Mission> getMissions() {
        return missions;
    }

}
