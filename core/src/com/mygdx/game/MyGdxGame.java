package com.mygdx.game;

import Logic.GameLogic;
import View.GameInput;
import View.GameView;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputMultiplexer;

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
		gameInput = new GameInput(gameLogic, gameView.viewComponents);

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(gameView.viewComponents.stage);
		inputMultiplexer.addProcessor(gameInput);
		input.setInputProcessor(inputMultiplexer);


		gameLogic.init();
        //logicThread = new Thread(gameLogic);
        //logicThread.run();


	}

	@Override
	public void render () {

		gameLogic.update();
		gameView.update();

	}
	
	@Override
	public void dispose () {

	}
}
