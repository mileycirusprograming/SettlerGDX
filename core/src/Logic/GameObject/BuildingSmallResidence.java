package Logic.GameObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by landfried on 03.02.17.
 */
public class BuildingSmallResidence extends Building {



    public BuildingSmallResidence() {

        constructionResources.put(ResourceType.STONE, 5);
        constructionResources.put(ResourceType.WOOD, 4);

    }


    @Override
    public void update() {
    }

}
