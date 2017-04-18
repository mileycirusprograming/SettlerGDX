package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionCarrier;
import com.sun.istack.internal.Nullable;

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
    protected List<ResourceType> productionResources;
    public List<Resource> producedResources;
    private long productionTime = 5000;
    private long nextProductionTime = System.currentTimeMillis();

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
        productionResources = new ArrayList<>();
        producedResources = new ArrayList<>();

        constructionRate = 0;
        builders = 0;
    }

    public void update() {
        if (System.currentTimeMillis() > nextProductionTime && state == BuildingState.BUILT) {
            produce();
            nextProductionTime = System.currentTimeMillis() + productionTime;
        }
    }

    protected abstract void produce();

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

    public Map<ResourceType, Integer> getProductionResources() {
        HashMap<ResourceType, Integer> producedResourcesMap = new HashMap<>();

        for (Resource resource : producedResources) {
            int amount = 8 - countStoredResources(resource.getType());
            if (amount > 0)
                producedResourcesMap.put(resource.getType(), amount);
        }

        return producedResourcesMap;
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
                return getProductionResources();

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

    protected Resource getStoredResource(ResourceType resourceType) {
        for (Resource resource : storedResources)
            if (resource.getType().equals(resourceType))
                return resource;
        return null;
    }
}
