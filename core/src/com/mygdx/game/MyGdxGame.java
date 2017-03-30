package com.mygdx.game;

import Logic.GameLogic;
import View.GameInput;
import View.GameView;
import View.InterfaceView;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputMultiplexer;

import static com.badlogic.gdx.Gdx.input;

public class MyGdxGame extends ApplicationAdapter {


	Thread logicThread;
	GameLogic gameLogic;
	GameView gameView;
	GameInput gameInput;
	InterfaceView interfaceView;



	@Override
	public void create () {

		gameLogic = new GameLogic();
		gameView = new GameView(gameLogic.getGameObjectContainer());
		gameInput = new GameInput(gameLogic, gameView.viewComponents);
		interfaceView = new InterfaceView(gameLogic.getGameObjectContainer());


		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(interfaceView.getStage());
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
		interfaceView.update();

	}
	
	@Override
	public void dispose () {

	}
}
