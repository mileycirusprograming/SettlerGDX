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

    }

    @Override
    public void begin() {
        super.begin();
    }

    @Override
    public void abort() {
        if (getState() == MissionState.WORK)
        super.abort();
        resource.disclaim();

    }

    @Override
    public void finish() {
        super.finish();
        resource.disclaim();

    }



    public Resource getResource() {
        return resource;
    }
}
