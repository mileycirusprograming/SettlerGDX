package View;

import Logic.GameObjectContainer;


/**
 * Created by Gustav on 12.02.2017.
 */
public class GameView {

    public ViewComponents viewComponents;
    private SettlerView settlerView;
    private ResourceView resourceView;

    public GameView(GameObjectContainer gameObjectContainer) {
        viewComponents = new ViewComponents(gameObjectContainer);
        settlerView = new SettlerView(viewComponents);
        resourceView = new ResourceView(viewComponents);
    }

    public void update() {

        settlerView.update();
        resourceView.update();

        viewComponents.refreshLayers();
        viewComponents.render();


    }



}
