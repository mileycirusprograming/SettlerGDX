package Logic;

import Logic.GameObject.Building;
import Logic.GameObject.Resource;
import Logic.GameObject.Settler;

import java.util.List;

/**
 * Created by landfried on 03.02.17.
 */
public class NationObjectAccessor {
    private int nationId;
    private GameObjectContainer gameObjectContainer;

    public NationObjectAccessor(int nationId, GameObjectContainer gameObjectContainer) {
        this.nationId = nationId;
        this.gameObjectContainer = gameObjectContainer;
    }

    public List<Building> getBuildings() {
        return gameObjectContainer.getBuildings(nationId);
    }

    public List<Settler> getSettlers() {
        return gameObjectContainer.getSettlers(nationId);

    }

    public List<Resource> getResources() {
        return gameObjectContainer.getResources(nationId);

    }
}
