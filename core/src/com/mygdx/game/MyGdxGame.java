package com.mygdx.game;

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

	AssetManager assetManager = new AssetManager();
	TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera = new OrthographicCamera();

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");




		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("desert.tmx", TiledMap.class);

		while (!assetManager.update()){
		}


		map = assetManager.get("desert.tmx");
		float unitScale = 1/64f;
		renderer = new OrthogonalTiledMapRenderer(map, unitScale);
		camera.setToOrtho(false, 20, 20);


	}

	@Override
	public void render () {


		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		*/

		camera.update();
		renderer.setView(camera);

		renderer.render();




	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
