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

    public Map<ResourceType, Integer> getConstructionResources(){
        return constructionResources;
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

    public void notifyShippingResource(ResourceType resourceType) {
        shippedResources.add(resourceType);
    }

    public  void notifyStopShippingResource(ResourceType resourceType) {
        shippedResources.remove(resourceType);
    }

    public void notifyMissionComplete(Mission mission) {
        if (mission instanceof MissionCarrier) {
            MissionCarrier missionCarrier = (MissionCarrier) mission;
            shippedResources.remove(((MissionCarrier) mission).getResource().getType());
            missionCarrier.getResource().claim();
            storedResources.add(missionCarrier.getResource());
        }
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
