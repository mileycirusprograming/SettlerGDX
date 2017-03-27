package View;

import Logic.GameObject.Building;
import Logic.GameObjectContainer;


/**
 * Created by Gustav on 12.02.2017.
 */
public class GameView {

    public ViewComponents viewComponents;
    private SettlerView settlerView;
    private ResourceView resourceView;
    private BuildingView buildingView;

    public GameView(GameObjectContainer gameObjectContainer) {
        viewComponents = new ViewComponents(gameObjectContainer);
        settlerView = new SettlerView(viewComponents);
        resourceView = new ResourceView(viewComponents);
        buildingView = new BuildingView(viewComponents);
    }

    public void update() {

        settlerView.update();
        resourceView.update();
        buildingView.update();

        //viewComponents.refreshLayers();
        viewComponents.render();


    }



}
