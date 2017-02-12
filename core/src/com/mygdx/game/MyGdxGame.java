package com.mygdx.game;

import Logic.GameLogic;
import Logic.GameObject.BuildingSmallResidence;
import View.GameView;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyGdxGame extends ApplicationAdapter {


	Thread logicThread;
	GameLogic gameLogic;

	GameView gameView;



	
	@Override
	public void create () {

		gameLogic = new GameLogic();
		//logicThread = new Thread(gameLogic);
		//logicThread.run();

		gameView = new GameView();
		gameView.init();

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
