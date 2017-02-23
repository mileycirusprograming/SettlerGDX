package com.mygdx.game;

import Logic.GameLogic;
import View.GameInput;
import View.GameView;
import com.badlogic.gdx.ApplicationAdapter;
import static com.badlogic.gdx.Gdx.input;

public class MyGdxGame extends ApplicationAdapter {


	Thread logicThread;
	GameLogic gameLogic;
	GameView gameView;
	GameInput gameInput;


	@Override
	public void create () {

		gameLogic = new GameLogic();
		gameView = new GameView(gameLogic.getGameObjectContainer());
		gameInput = new GameInput(gameLogic, gameView);

		gameLogic.init();
		gameView.init();
        //logicThread = new Thread(gameLogic);
        //logicThread.run();

        input.setInputProcessor(gameInput);

	}

	@Override
	public void render () {

		//gameLogic.update();
		gameView.update();

	}
	
	@Override
	public void dispose () {

	}
}
