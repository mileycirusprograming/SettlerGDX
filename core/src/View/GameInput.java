package View;

import Logic.Break;
import Logic.GameLogic;
import Logic.GameObject.BuildingSmallResidence;
import Logic.GameObject.ObjectPosition;
import Logic.GameObject.SettlerCarrier;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Gustav on 23.02.2017.
 */
public class GameInput implements InputProcessor {
    private GameLogic gameLogic;
    private GameView gameView;
    private ViewComponents viewComponents;
    private boolean touchDown;
    private float lastX, lastY;
    private int button;

    public GameInput(GameLogic gameLogic, ViewComponents viewComponents) {
        this.gameLogic = gameLogic;
        this.viewComponents = viewComponents;
        lastX = viewComponents.camera.position.x;
        lastY = viewComponents.camera.position.y;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F1)
            Break.toggle();
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastX = screenX;
        lastY = screenY;
        this.button = button;
        if (button == Input.Buttons.LEFT) {
            BuildingSmallResidence buildingSmallResidence = new BuildingSmallResidence();
            ObjectPosition worldCoordinates = viewComponents.getWorldCoordinates(screenX, screenY);
            buildingSmallResidence.getPosition().x = worldCoordinates.x;
            buildingSmallResidence.getPosition().y = worldCoordinates.y;
            //gameLogic.getGameObjectContainer().addBuilding(buildingSmallResidence);
            SettlerCarrier settler = new SettlerCarrier();
            settler.getPosition().x = worldCoordinates.x;
            settler.getPosition().y = worldCoordinates.y;
            settler.setNationId(0);
            gameLogic.getGameObjectContainer().addSettler(settler);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (button == Input.Buttons.RIGHT) {
            viewComponents.camera.position.x += (lastX - screenX) * 0.04;
            viewComponents.camera.position.y -= (lastY - screenY) * 0.04;
            lastX = screenX;
            lastY = screenY;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        viewComponents.camera.zoom += amount * 0.1 * viewComponents.camera.zoom;
        return false;
    }
}
