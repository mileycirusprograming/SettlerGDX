package View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

/**
 * Created by Gustav on 12.02.2017.
 */
public class GameView {

    AssetManager assetManager = new AssetManager();
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera = new OrthographicCamera();


    public void init() {
        assetManager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
        assetManager.load("SettlerSprite.png", Texture.class);


        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("TestMap2.tmx", TiledMap.class);

        while (!assetManager.update()){}

        map = new TmxMapLoader().load("TestMap2.tmx");
        MapLayers layers = map.getLayers();

        Texture settlerSprite = new Texture(Gdx.files.internal("SettlerSprite.png"));
        TextureRegion grassTextureRegion = new TextureRegion(settlerSprite);


        float unitScale = 1/32f;
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        camera.setToOrtho(false, map.getProperties().get("width", Integer.class), map.getProperties().get("height", Integer.class));
    }


    public void update() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);

        //int[] layers = {0, 1};
        renderer.render();

    }
}
