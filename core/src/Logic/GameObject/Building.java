package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionCarrier;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by landfried on 03.02.17.
 */
public abstract class Building extends GameObject {
    private BuildingState state;
    private List<ResourceType> shippedResources;
    private List<Resource> storedResources;



    public Building() {
        state = BuildingState.CONSTRUCT;

        shippedResources = new ArrayList<>();
        storedResources = new ArrayList<>();
    }

    public abstract void update();

    public abstract Map<ResourceType, Integer> constructionResources();
    public abstract Map<ResourceType, Integer> productionResources();



    public Map<ResourceType, Integer> getNeededResources() {
        switch (state) {
            case CONSTRUCT:
                return constructionResources();

            case PRODUCTION:
                return productionResources();

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
                    storedResources.add(missionCarrier.getResource());
                    break;
            }
        }
    }
}
