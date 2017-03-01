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
        BuildingSmallResidence smallRes1 = new BuildingSmallResidence();
        smallRes1.setNationId(0);
        smallRes1.getPosition().x = 20;
        smallRes1.getPosition().y = 10;

        SettlerCarrier car1 = new SettlerCarrier();
        car1.getPosition().x = 15;
        car1.getPosition().y = 15;
        car1.setNationId(0);
        Resource res1 = new Resource(ResourceType.STONE);
        res1.setNationId(0);
        Resource res2 = new Resource(ResourceType.STONE);
        res2.setNationId(0);
        Resource res3 = new Resource(ResourceType.WOOD);
        res3.setNationId(0);


        gameObjectContainer.createNation();
        gameObjectContainer.addBuilding(smallRes1);
        //gameObjectContainer.addSettler(car1);
        gameObjectContainer.addResource(res1);
        gameObjectContainer.addResource(res2);
        gameObjectContainer.addResource(res3);
    }

    public void update() {

        //Break.stop();

        gameObjectContainer.updateNations();
        gameObjectContainer.updateSettlers();
        gameObjectContainer.updateBuildings();

        settlerMovement();

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
        long currentTime = System.currentTimeMillis();

        for (Settler settler : gameObjectContainer.getSettlers()) {
            if (settler.nextMoveTime > currentTime)
                continue;

            if (settler instanceof SettlerCarrier) {
                SettlerCarrier carrier = (SettlerCarrier)settler;

                ObjectPosition delta = new ObjectPosition(carrier.getPosition());
                ObjectPosition dir = new ObjectPosition(carrier.getDirection());
                delta.x = (Math.abs(dir.x) >= Math.abs(dir.y)) ? sign(dir.x) : 0;
                delta.y = (Math.abs(dir.x) < Math.abs(dir.y)) ? sign(dir.y) : 0;

                carrier.move(delta);

            }
            settler.nextMoveTime += 400;
        }
    }

    private int sign(int var0) {
        if (var0 > 0) return 1;
        if (var0 < 0) return -1;
        return 0;
    }

    private void randomSettlerMoving() {
        Random random = new Random();
        long currentTime = System.currentTimeMillis();

        for (Settler settler : gameObjectContainer.getSettlers()) {
            if (settler.nextMoveTime > currentTime)
                continue;

            ObjectPosition delta = new ObjectPosition();
            delta.x = (int)(random.nextFloat()*3) - 1;
            delta.y = (int)(random.nextFloat()*3) - 1;

            settler.move(delta);
            settler.nextMoveTime = System.currentTimeMillis() + (long)(random.nextFloat()*200 + 400);
        }
    }
}
