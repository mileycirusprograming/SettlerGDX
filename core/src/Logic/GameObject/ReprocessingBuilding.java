package Logic.GameObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Franken on 12.02.2017.
 */
public abstract class ReprocessingBuilding extends Building {

    private List<ResourceType> baseResources;

    public Map<ResourceType, Integer> getNeededResources() {

        Map<ResourceType, Integer> neededBaseResources = new HashMap<>();
        /*@ToDo
         Calculate neededBaseResources with storedResources and baseResources
         */

        switch (state) {
            case CONSTRUCT:
                return getConstructionResources();

            case PRODUCTION:
                return neededBaseResources;

            case SLEEP:
                return new HashMap<>();
        }

        return new HashMap<>();
    }

}
