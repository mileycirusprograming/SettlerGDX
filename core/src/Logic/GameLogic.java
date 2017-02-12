package Logic;

import Logic.GameObject.*;

/**
 * Created by landfried on 30.01.17.
 */
public class GameLogic {
    public boolean run;
    private GameObjectContainer gameObjectContainer;

    public GameLogic() {
        run = true;
        gameObjectContainer = new GameObjectContainer();

    }

    public void init() {
        BuildingSmallResidence smallRes1 = new BuildingSmallResidence();
        smallRes1.setNationId(0);
        SettlerCarrier car1 = new SettlerCarrier();
        car1.setNationId(0);
        Resource res1 = new Resource(ResourceType.STONE);
        res1.setNationId(0);
        Resource res2 = new Resource(ResourceType.STONE);
        res2.setNationId(0);
        Resource res3 = new Resource(ResourceType.WOOD);
        res3.setNationId(0);


        gameObjectContainer.createNation();
        gameObjectContainer.addBuilding(smallRes1);
        gameObjectContainer.addSettler(car1);
        gameObjectContainer.addResource(res1);
        gameObjectContainer.addResource(res2);
        gameObjectContainer.addResource(res3);
    }

    public void update() {
        gameObjectContainer.updateNations();
        gameObjectContainer.updateSettlers();
        gameObjectContainer.updateBuildings();

        run = false;
    }

    public void createNation() {
        gameObjectContainer.createNation();
    }




}
