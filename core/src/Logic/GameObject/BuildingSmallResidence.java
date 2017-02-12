package Logic.GameObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by landfried on 03.02.17.
 */
public class BuildingSmallResidence extends Building {

    public BuildingSmallResidence() {

    }



    @Override
    public void update() {
    }

    @Override
    public Map<ResourceType, Integer> constructionResources() {
        HashMap<ResourceType, Integer> resources = new HashMap<>();
        resources.put(ResourceType.STONE, 5);
        resources.put(ResourceType.WOOD, 4);
        return resources;
    }

    @Override
    public Map<ResourceType, Integer> productionResources() {
        HashMap<ResourceType, Integer> resources = new HashMap<>();
        return resources;
    }
}
