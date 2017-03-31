package Controller;

import Logic.GameObjectContainer;
import View.InterfaceView;
import View.ViewComponents;

/**
 * Created by landfried on 31.03.17.
 */
public class MainController {
    private ViewComponents viewComponents;
    private GameObjectContainer gameObjectContainer;
    private InterfaceView interfaceView;

    private ObjectChooserController objectChooserController;

    public MainController(ViewComponents viewComponents, GameObjectContainer gameObjectContainer, InterfaceView interfaceView) {
        this.viewComponents = viewComponents;
        this.gameObjectContainer = gameObjectContainer;
        this.interfaceView = interfaceView;

        objectChooserController = new ObjectChooserController(viewComponents, gameObjectContainer);
        interfaceView.subscribeButtons(objectChooserController);
    }

    public ObjectChooserController getObjectChooserController() {
        return objectChooserController;
    }
}
