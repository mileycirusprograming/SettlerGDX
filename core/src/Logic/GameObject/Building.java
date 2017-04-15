package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionCarrier;

import java.io.Serializable;
import java.util.*;

/**
 * Created by landfried on 03.02.17.
 */
public abstract class Building extends GameObject {
    BuildingState state;
    private List<ResourceType> shippedResources;
    private List<Resource> storedResources;
    protected Map<ResourceType, Integer> constructionResources;
    private int constructionRate;
    public int builders;

    public BuildingState getState() {
        return state;
    }

    public Building() {
        super();
        state = BuildingState.CONSTRUCT;

        shippedResources = new ArrayList<>();
        storedResources = new ArrayList<>();
        constructionResources = new HashMap<>();

        constructionRate = 0;
        builders = 0;
    }

    public void update() {

    }

    @Override
    public HashMap<String, Serializable> getProperties() {
        HashMap<String, Serializable> properties = new HashMap<>();
        properties.putAll(super.getProperties());
        properties.put("state", state);

        return  properties;
    }

    public Map<ResourceType, Integer> getConstructionResources() {
        HashMap<ResourceType, Integer> missingResources = new HashMap<>();
        for (ResourceType type : constructionResources.keySet()) {
            missingResources.put(type, constructionResources.get(type) - countAllResources(type));
        }

        missingResources.values().removeIf(resource -> resource <= 0);

            return missingResources;
    }

    private int countStoredResources(ResourceType countedType) {
        int result = 0;
        for (Resource resource : storedResources)
            if (resource.getType() == countedType)
                result++;
        return result;
    }

    private int countAllResources(ResourceType countedType) {
        int result = 0;
        for (ResourceType type : shippedResources)
            if (type == countedType)
                result++;

        result += countStoredResources(countedType);

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

    public boolean waitingForContruction() {
        if (state.equals(BuildingState.CONSTRUCT))
            if (!storedResources.isEmpty())
                return true;
        return  false;
    }

    public void construct() {
        if (constructionRate < 50) {
            if (getNeededResources().isEmpty() && shippedResources.isEmpty())
                constructionRate += 1;
        } else {
            state = BuildingState.BUILT;
        }

    }
}
