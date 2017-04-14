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

    private void settlerMovement() {
       /*
        long currentTime = System.currentTimeMillis();

        for (Settler settler : gameObjectContainer.getSettlers()) {
            if (settler.nextMoveTime > currentTime)
                continue;

            ObjectPosition delta = new ObjectPosition(settler.getPosition());
            ObjectPosition dir = new ObjectPosition(settler.getDirection());
            delta.x = (Math.abs(dir.x) >= Math.abs(dir.y)) ? sign(dir.x) : 0;
            delta.y = (Math.abs(dir.x) < Math.abs(dir.y)) ? sign(dir.y) : 0;
            settler.move(delta);

            settler.nextMoveTime += 200;
        }
        */
    }

    private int sign(int var0) {
        if (var0 > 0) return 1;
        if (var0 < 0) return -1;
        return 0;
    }


}
