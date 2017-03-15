package Logic.Mission;

import Logic.GameObject.Building;
import Logic.GameObject.Resource;

/**
 * Created by landfried on 03.02.17.
 */
public class MissionCarrier extends Mission {
    private Resource resource;


    public MissionCarrier(Building building, Resource resource) {
        super(building);
        this.resource = resource;
        resource.claim();
        getBuilding().notifyShippingResource(getResource().getType());

    }

    @Override
    public void begin() {
        super.begin();
    }

    @Override
    public void abort() {
        if (getState() == MissionState.WORK)
            getBuilding().notifyStopShippingResource(getResource().getType());
        super.abort();
        resource.disclaim();

    }

    @Override
    public void finish() {
        super.finish();
        getBuilding().notifyMissionComplete(this);
        resource.disclaim();

    }



    public Resource getResource() {
        return resource;
    }
}
