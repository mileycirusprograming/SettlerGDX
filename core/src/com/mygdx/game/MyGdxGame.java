package com.mygdx.game;

import Logic.GameLogic;
import View.GameController;
import View.GameView;
import com.badlogic.gdx.ApplicationAdapter;
import static com.badlogic.gdx.Gdx.input;

public class MyGdxGame extends ApplicationAdapter {


	Thread logicThread;
	GameLogic gameLogic;
	GameView gameView;
	GameController gameController;





	
	@Override
	public void create () {

		gameLogic = new GameLogic();
		gameView = new GameView();
		gameController = new GameController(gameLogic, gameView);

		gameLogic.init();
		gameView.init();
        //logicThread = new Thread(gameLogic);
        //logicThread.run();

        input.setInputProcessor(gameController);

	}

	@Override
	public void render () {

		//gameLogic.update();
		gameController.update();
		gameView.update();

	}
	
	@Override
	public void dispose () {

	}
}
