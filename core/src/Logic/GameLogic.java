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
        smallRes1.getPosition().x = 40;
        smallRes1.getPosition().y = 40;

        SettlerCarrier car1 = new SettlerCarrier();
        car1.getPosition().x = 15;
        car1.getPosition().y = 15;
        car1.setNationId(0);
        Resource res1 = new Resource(ResourceType.STONE);
        res1.setNationId(0);
        res1.getPosition().x = 10;
        res1.getPosition().y = 14;
        Resource res2 = new Resource(ResourceType.STONE);
        res2.setNationId(0);
        res2.getPosition().x = 20;
        res2.getPosition().y = 4;
        Resource res3 = new Resource(ResourceType.WOOD);
        res3.setNationId(0);
        res3.getPosition().x = 18;
        res3.getPosition().y = 15;
        Resource res4 = new Resource(ResourceType.WOOD);
        res4.setNationId(0);
        res4.getPosition().x = 30;
        res4.getPosition().y = 28;
        Resource res5 = new Resource(ResourceType.STONE);
        res5.setNationId(0);
        res5.getPosition().x = 5;
        res5.getPosition().y = 31;
        Resource res6 = new Resource(ResourceType.WOOD);
        res6.setNationId(0);
        res6.getPosition().x = 23;
        res6.getPosition().y = 13;
        Resource res7 = new Resource(ResourceType.WOOD);
        res7.setNationId(0);
        res7.getPosition().x = 50;
        res7.getPosition().y = 30;
        Resource res8 = new Resource(ResourceType.STONE);
        res8.setNationId(0);
        res8.getPosition().x = 22;
        res8.getPosition().y = 51;
        Resource res9 = new Resource(ResourceType.STONE);
        res9.setNationId(0);
        res9.getPosition().x = 52;
        res9.getPosition().y = 53;



        gameObjectContainer.createNation();
        gameObjectContainer.addBuilding(smallRes1);
        //gameObjectContainer.addSettler(car1);
        gameObjectContainer.addResource(res1);
        gameObjectContainer.addResource(res2);
        gameObjectContainer.addResource(res3);
        gameObjectContainer.addResource(res4);
        gameObjectContainer.addResource(res5);
        gameObjectContainer.addResource(res6);
        gameObjectContainer.addResource(res7);
        gameObjectContainer.addResource(res8);
        gameObjectContainer.addResource(res9);


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
            settler.nextMoveTime += 200;
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
