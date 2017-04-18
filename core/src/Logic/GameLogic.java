package Logic;

import Logic.GameObject.*;

import java.util.Random;

/**
 * Created by landfried on 30.01.17.
 */
public class GameLogic implements Runnable {
    public boolean running;
    private GameObjectContainer gameObjectContainer;

    public GameLogic() {
        running = true;
        gameObjectContainer = new GameObjectContainer();

    }

    public void init() {

        gameObjectContainer.createNation();

    }

    public void update() {

        //Break.stop();

        gameObjectContainer.updateNations();
        gameObjectContainer.updateSettlers();
        gameObjectContainer.updateBuildings();
        gameObjectContainer.addProductionResources();
        gameObjectContainer.removeProcessedResources();

        //settlerMovement();

        //running = false;
    }

    public void createNation() {
        gameObjectContainer.createNation();
    }

    public GameObjectContainer getGameObjectContainer() {
        return gameObjectContainer;
    }

    @Override
    public void run() {
        init();

        while (running) {
            update();
        }
    }




}
