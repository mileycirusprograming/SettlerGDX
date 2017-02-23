package View;

import Logic.GameObject.Building;
import Logic.GameObject.GameObject;
import Logic.GameObject.ObjectPosition;
import Logic.GameObject.Settler;
import Logic.GameObjectContainer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gustav on 12.02.2017.
 */
public class GameView {

    private final String BUILDING_LAYER = "Buildings";
    private final String SETTLER_LAYER = "Settlers";


    private GameObjectContainer gameObjectContainer;
    AssetManager assetManager = new AssetManager();
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera = new OrthographicCamera();
    SpriteBatch batch;
    Sprite settlerSpriteTexture;
    private Map<Integer, Boolean> buildingBitmap;
    private Map<Integer, ObjectPosition> settlerBitmap;
    private TiledMapTileSet tileSet;

    public GameView(GameObjectContainer gameObjectContainer) {
        this.gameObjectContainer = gameObjectContainer;
    }

    public void init() {
        assetManager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
        assetManager.load("SettlerSprite.png", Texture.class);


        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("TestMap2.tmx", TiledMap.class);

        while (!assetManager.update()){}

        map = new TmxMapLoader().load("TestMap3.tmx");
        MapLayers layers = map.getLayers();

        settlerSpriteTexture = new Sprite(new Texture(Gdx.files.internal("SettlerSprite.png")));
        batch = new SpriteBatch();


        float unitScale = 1/32f;
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        camera.setToOrtho(false, map.getProperties().get("width", Integer.class), map.getProperties().get("height", Integer.class));

        buildingBitmap = new HashMap<>();
        settlerBitmap = new HashMap<>();

        tileSet = map.getTileSets().getTileSet("TestMapSet4");
    }


    public void update() {

        refreshBuildingLayer(gameObjectContainer.getBuildings());
        refreshSettlerLayer(gameObjectContainer.getSettlers());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);

        //int[] layers = {0, 1};
        renderer.render();

        /*
        batch.begin();
        settlerSpriteTexture.draw(batch);
        batch.end();
        */
    }

    public void refreshBuildingLayer(List<Building> buildings) {
        for (Building building : buildings) {
            if (buildingBitmap.containsKey(building.hashCode()))
                if (buildingBitmap.get(building.hashCode()))
                    continue;
            createBuilding(building);
        }
    }

    public void refreshSettlerLayer(List<Settler> settlers) {
        for (Settler settler : settlers) {
            if (!settlerBitmap.containsKey(settler.hashCode()))
                createSettler(settler);

            if (settler.moved)
                moveSettler(settler);
        }
    }

    private void createBuilding(Building building) {
        int x = building.getPosition().x;
        int y = building.getPosition().y;

        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(BUILDING_LAYER);
        TiledMapTileLayer.Cell cell[] = new TiledMapTileLayer.Cell[4];

        for (int i = 0; i <= 3; i++)
            cell[i] = new TiledMapTileLayer.Cell();

        cell[0].setTile(tileSet.getTile(2));
        layer.setCell(x, y, cell[0]);
        cell[1].setTile(tileSet.getTile(3));
        layer.setCell(x+1, y, cell[1]);
        cell[2].setTile(tileSet.getTile(5));
        layer.setCell(x, y-1, cell[2]);
        cell[3].setTile(tileSet.getTile(6));
        layer.setCell(x+1, y-1, cell[3]);

        buildingBitmap.put(building.hashCode(), true);
    }

    private void createSettler(Settler settler) {
        int x = settler.getPosition().x;
        int y = settler.getPosition().y;

        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(SETTLER_LAYER);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

        cell.setTile(tileSet.getTile(7));
        layer.setCell(x, y, cell);

        settlerBitmap.put(settler.hashCode(), new ObjectPosition(settler.getPosition()));
    }

    public void moveSettler(Settler settler) {

        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(SETTLER_LAYER);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tileSet.getTile(7));

        layer.setCell(settlerBitmap.get(settler.hashCode()).x, settlerBitmap.get(settler.hashCode()).y, null);
        layer.setCell(settler.getPosition().x, settler.getPosition().y, cell);

        settlerBitmap.put(settler.hashCode(), new ObjectPosition(settler.getPosition()));
        settler.moved = false;
    }


    public ObjectPosition getWorldCoordinates(int x, int y) {
        Vector3 vector = new Vector3();
        vector.x = x;
        vector.y = y;
        vector = camera.unproject(vector);
        ObjectPosition position = new ObjectPosition();
        position.x = (int)vector.x;
        position.y = (int)vector.y;
        return position;
    }


}
