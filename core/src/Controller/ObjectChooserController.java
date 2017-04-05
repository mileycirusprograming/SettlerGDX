package Controller;

import Logic.GameObject.*;
import Logic.GameObjectContainer;
import View.ViewComponents;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by landfried on 31.03.17.
 */
public class ObjectChooserController extends ChangeListener {
    private ViewComponents viewComponents;
    private GameObjectContainer gameObjectContainer;
    private Tool tool;

    public ObjectChooserController(ViewComponents viewComponents, GameObjectContainer gameObjectContainer) {
        this.viewComponents = viewComponents;
        this.gameObjectContainer = gameObjectContainer;

        tool = new CarrierTool();
    }

    public Tool getTool() {
        return tool;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if (!(actor instanceof TextButton))
            return;
        TextButton textButton = (TextButton) actor;
        if (!textButton.isChecked())
            return;

        if (textButton.getLabel().textEquals("Carrier"))
            tool = new CarrierTool();
        if (textButton.getLabel().textEquals("Builder"))
            tool = new BuilderTool();
        if (textButton.getLabel().textEquals("SmallResidence"))
            tool = new SmallResidenceTool();
        if (textButton.getLabel().textEquals("Wood"))
            tool = new WoodTool();
        if (textButton.getLabel().textEquals("Stone"))
            tool = new StoneTool();

    }

    public abstract class Tool {
        public abstract void place(int screenX, int screenY);
    }

    private class CarrierTool extends Tool {
        @Override
        public void place(int screenX, int screenY) {
            ObjectPosition worldCoordinates = viewComponents.getWorldCoordinates(screenX, screenY);
            SettlerCarrier settler = new SettlerCarrier();
            settler.getPosition().x = worldCoordinates.x;
            settler.getPosition().y = worldCoordinates.y;
            settler.setNationId(0);
            gameObjectContainer.addSettler(settler);
        }
    }

    private class BuilderTool extends Tool {
        @Override
        public void place(int screenX, int screenY) {
            ObjectPosition worldCoordinates = viewComponents.getWorldCoordinates(screenX, screenY);
            SettlerBuilder settler = new SettlerBuilder();
            settler.getPosition().x = worldCoordinates.x;
            settler.getPosition().y = worldCoordinates.y;
            settler.setNationId(0);
            gameObjectContainer.addSettler(settler);
        }
    }

    private class SmallResidenceTool extends Tool {
        @Override
        public void place(int screenX, int screenY) {
            BuildingSmallResidence buildingSmallResidence = new BuildingSmallResidence();
            ObjectPosition worldCoordinates = viewComponents.getWorldCoordinates(screenX, screenY);
            buildingSmallResidence.getPosition().x = worldCoordinates.x;
            buildingSmallResidence.getPosition().y = worldCoordinates.y;
            gameObjectContainer.addBuilding(buildingSmallResidence);
        }
    }

    private  class WoodTool extends Tool {
        @Override
        public void place(int screenX, int screenY) {
            Resource wood = new Resource(ResourceType.WOOD);
            ObjectPosition worldCoordinates = viewComponents.getWorldCoordinates(screenX, screenY);
            wood.getPosition().x = worldCoordinates.x;
            wood.getPosition().y = worldCoordinates.y;
            gameObjectContainer.addResource(wood);
        }
    }

    private  class StoneTool extends Tool {
        @Override
        public void place(int screenX, int screenY) {
            Resource stone = new Resource(ResourceType.STONE);
            ObjectPosition worldCoordinates = viewComponents.getWorldCoordinates(screenX, screenY);
            stone.getPosition().x = worldCoordinates.x;
            stone.getPosition().y = worldCoordinates.y;
            gameObjectContainer.addResource(stone);
        }
    }

}

