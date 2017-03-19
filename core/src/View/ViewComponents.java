package View;

import Logic.GameObject.*;
import Logic.GameObjectContainer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by landfried on 05.03.17.
 */
public class ViewComponents {

    public final String BUILDING_LAYER = "Buildings";
    public final String SETTLER_LAYER = "Settlers";
    public final String RESOURCE_LAYER = "Resources";


    public GameObjectContainer gameObjectContainer;
    AssetManager assetManager = new AssetManager();
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera = new OrthographicCamera();
    private Map<Integer, Boolean> buildingBitmap;
    TiledMapTileSet tileSet;
    public Stage stage;

    public ViewComponents(GameObjectContainer gameObjectContainer) {
        this.gameObjectContainer = gameObjectContainer;
        init();
    }

    public void init() {

        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("TestMap5.tmx", TiledMap.class);

        while (!assetManager.update()) {
        }

        map = new TmxMapLoader().load("TestMap5.tmx");
        MapLayers layers = map.getLayers();


        float unitScale = 1 / 32f;
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        camera.setToOrtho(false, map.getProperties().get("width", Integer.class), map.getProperties().get("height", Integer.class));

        buildingBitmap = new HashMap<>();

        tileSet = map.getTileSets().getTileSet("TestMapSet5");

        stage = new Stage();

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        Skin skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton button = new TextButton("Nicht druecken!", skin);
        table.add(button);

        stage.addActor(table);
        //renderer.setView((OrthographicCamera)stage.getCamera());

    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        stage.act();
        stage.draw();

    }


    private void refreshBuildingLayer(List<Building> buildings) {
        for (Building building : buildings) {
            if (buildingBitmap.containsKey(building.hashCode()))
                if (buildingBitmap.get(building.hashCode()))
                    continue;
            createBuilding(building);
        }
    }


    private void createBuilding(Building building) {
        int x = building.getPosition().x;
        int y = building.getPosition().y;

        TiledMapTileLayer layer = getLayer(BUILDING_LAYER);
        TiledMapTileLayer.Cell cell[] = new TiledMapTileLayer.Cell[4];

        for (int i = 0; i <= 3; i++)
            cell[i] = new TiledMapTileLayer.Cell();

        cell[0].setTile(tileSet.getTile(2));
        layer.setCell(x, y, cell[0]);
        cell[1].setTile(tileSet.getTile(3));
        layer.setCell(x + 1, y, cell[1]);
        cell[2].setTile(tileSet.getTile(5));
        layer.setCell(x, y - 1, cell[2]);
        cell[3].setTile(tileSet.getTile(6));
        layer.setCell(x + 1, y - 1, cell[3]);

        buildingBitmap.put(building.hashCode(), true);
    }


    ObjectPosition getWorldCoordinates(int x, int y) {
        Vector3 vector = new Vector3();
        vector.x = x;
        vector.y = y;
        vector = camera.unproject(vector);
        ObjectPosition position = new ObjectPosition();
        position.x = (int) vector.x;
        position.y = (int) vector.y;
        return position;
    }

    public void refreshLayers() {

        refreshBuildingLayer(gameObjectContainer.getBuildings());

    }

    public TiledMapTileLayer getLayer(String layerName) {
        return (TiledMapTileLayer)map.getLayers().get(layerName);
    }


}
