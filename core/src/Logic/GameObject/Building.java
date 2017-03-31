package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionCarrier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by landfried on 03.02.17.
 */
public abstract class Building extends GameObject {
    protected BuildingState state;
    private List<ResourceType> shippedResources;
    private List<Resource> storedResources;
    protected Map<ResourceType, Integer> constructionResources;



    public Building() {
        super();
        state = BuildingState.CONSTRUCT;

        shippedResources = new ArrayList<>();
        storedResources = new ArrayList<>();
        constructionResources = new HashMap<>();
    }

    public abstract void update();

    public Map<ResourceType, Integer> getConstructionResources() {
        HashMap<ResourceType, Integer> missingResources = new HashMap<>();
        for (ResourceType type : constructionResources.keySet()) {
            missingResources.put(type, constructionResources.get(type) - countAllResources(type));
        }
        return missingResources;
    }

    private int countAllResources(ResourceType countedType) {
        int result = 0;
        for (ResourceType type : shippedResources)
            if (type == countedType)
                result++;

        for (Resource resource : storedResources)
            if (resource.getType() == countedType)
                result++;

        return result;
    }

    public Map<ResourceType, Integer> getNeededResources() {
        switch (state) {
            case CONSTRUCT:
                return getConstructionResources();

            case BUILT:
                return new HashMap<>();

            case SLEEP:
                return new HashMap<>();
        }

        return new HashMap<>();
    }

    public void missionStateChanged(Mission mission) {
        if (mission instanceof MissionCarrier) {
            MissionCarrier missionCarrier = (MissionCarrier)mission;
            ResourceType resourceType = missionCarrier.getResource().getType();

            switch (mission.getState()) {
                case WORK:
                    shippedResources.add(resourceType);
                    break;
                case FAIL:
                    shippedResources.remove(resourceType);
                    break;
                case COMPLETE:
                    shippedResources.remove(resourceType);
                    missionCarrier.getResource().claim();
                    storedResources.add(missionCarrier.getResource());
                    break;
            }
        }
    }
}
