package View;

import Logic.GameLogic;
import Logic.GameObject.Building;
import Logic.GameObject.BuildingSmallResidence;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Gustav on 16.02.2017.
 */
public class GameController implements InputProcessor {
    private GameLogic gameLogic;
    private GameView gameView;
    private boolean touchDown;
    private float lastX, lastY;
    private int button;

    public GameController(GameLogic gameLogic, GameView gameView) {
        this.gameLogic = gameLogic;
        this.gameView = gameView;
        lastX = gameView.camera.position.x;
        lastY = gameView.camera.position.y;
    }

    public void update() {
        gameView.refreshBuildingLayer(gameLogic.getGameObjectContainer().getBuildings());
    }

    @Override
    public boolean keyDown(int keycode) {
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
            Vector2 worldCoordinates = gameView.getWorldCoordinates(screenX, screenY);
            buildingSmallResidence.getPosition().x = (int)worldCoordinates.x;
            buildingSmallResidence.getPosition().y = (int)worldCoordinates.y;
            gameLogic.getGameObjectContainer().addBuilding(buildingSmallResidence);
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
            gameView.camera.position.x += (lastX - screenX) * 0.04;
            gameView.camera.position.y -= (lastY - screenY) * 0.04;
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
        gameView.camera.zoom += amount * 0.02;
        return false;
    }
}
