package View;

import Logic.GameLogic;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Gustav on 16.02.2017.
 */
public class GameController implements InputProcessor {
    private GameLogic gameLogic;
    private GameView gameView;
    private boolean touchDown;
    private float lastX, lastY;

    public GameController(GameLogic gameLogic, GameView gameView) {
        this.gameLogic = gameLogic;
        this.gameView = gameView;
        lastX = gameView.camera.position.x;
        lastY = gameView.camera.position.y;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        gameView.camera.position.x += (lastX - screenX) * 0.04;
        gameView.camera.position.y -= (lastY - screenY) * 0.04;
        lastX = screenX;
        lastY = screenY;
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
