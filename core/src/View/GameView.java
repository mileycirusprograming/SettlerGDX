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
    OrthographicCamera camera = new OrthographicCamera();


    public void init() {
        assetManager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
        assetManager.load("tile.png", Texture.class);
        assetManager.load("settler.png", Texture.class);

        while (!assetManager.update()){}

        map = new TiledMap();
        MapLayers layers = map.getLayers();

        Texture grassTexture = new Texture(Gdx.files.internal("tile.png"));
        TextureRegion grassTextureRegion = new TextureRegion(grassTexture);

        Texture settlerTexture = new Texture(Gdx.files.internal("settler.png"));
        TextureRegion settlerTextureRegion = new TextureRegion(settlerTexture);




        TiledMapTileLayer grassLayer = new TiledMapTileLayer(150, 100, 32, 32);
        for (int x = 0; x < 150; x++) {
            for (int y = 0; y < 100; y++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(new StaticTiledMapTile(grassTextureRegion));
                grassLayer.setCell(x, y, cell);
            }
        }
        layers.add(grassLayer);

        TiledMapTileLayer settlerLayer = new TiledMapTileLayer(150, 100, 32, 32);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(new StaticTiledMapTile(settlerTextureRegion));
        grassLayer.setCell(20, 20, cell);
        layers.add(settlerLayer);





        float unitScale = 1/64f;
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        camera.setToOrtho(false, 20, 20);
    }


    public void update() {

        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);

        int[] layers = {0, 1};
        renderer.render(layers);

    }
}
