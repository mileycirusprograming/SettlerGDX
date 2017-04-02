package com.mygdx.game;

import Controller.MainController;
import Logic.GameLogic;
import View.GameInput;
import View.GameView;
import View.InterfaceView;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import static com.badlogic.gdx.Gdx.input;

public class MyGdxGame extends ApplicationAdapter {


	Thread logicThread;
	private GameLogic gameLogic;
	private GameView gameView;
	private GameInput gameInput;
	private InterfaceView interfaceView;
	private MainController mainController;




	@Override
	public void create () {

		//Gdx.graphics.setWindowedMode(1000, 500);


		gameLogic = new GameLogic();
		gameView = new GameView(gameLogic.getGameObjectContainer());
		interfaceView = new InterfaceView();
		mainController = new MainController(gameView.viewComponents, gameLogic.getGameObjectContainer(), interfaceView);
		gameInput = new GameInput(gameLogic, gameView.viewComponents, mainController);

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

	@Override
	public void resize( int width, int height) {
		gameView.viewComponents.camera.viewportHeight = height;
		gameView.viewComponents.camera.viewportWidth = width;
        //gameView.viewComponents.camera.position.x = width * 0.1f;

//		interfaceView.getStage().getViewport().update(width, height, true);
		interfaceView.resize(width, height);
	}
}
