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
        resource.disclaim();
        super.abort();
    }

    @Override
    public void finish() {
        resource.disclaim();
        super.finish();
    }

    public Resource getResource() {
        return resource;
    }
}
