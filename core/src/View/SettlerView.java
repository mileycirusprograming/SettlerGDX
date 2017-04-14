package View;

import Logic.GameObject.Settler;
import Logic.GameObject.SettlerBuilder;
import Logic.GameObject.SettlerCarrier;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landfried on 03.03.17.
 */
public class SettlerView {
    private ViewComponents viewComponents;

    private TiledMapTile tile;
    private ArrayList<TiledMapTileLayer.Cell> cells;
    private TiledMapTileLayer layer;
    private int tileIndex = 11;

    private List<Settler> settlers;


    public SettlerView(ViewComponents viewComponents) {
        this.viewComponents = viewComponents;
        tile = viewComponents.tileSet.getTile(tileIndex);
        cells = new ArrayList<>();
        layer = viewComponents.getLayer(viewComponents.SETTLER_LAYER);
        settlers = viewComponents.gameObjectContainer.getSettlers();
    }

    public void update() {
        clearCells();
        drawAllSettlers();
    }


    private void putCell(Settler settler) {
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        setTile(settler);
        cell.setTile(tile);
        cells.add(cell);
        layer.setCell(settler.getPosition().x, settler.getPosition().y, cell);
    }

    private void clearCells() {
        for (TiledMapTileLayer.Cell cell : cells)
            cell.setTile(null);
        cells.clear();
    }

    private void drawAllSettlers() {
        for (Settler settler : settlers)
            putCell(settler);
    }

    private  void setTile(Settler settler) {
        if (settler instanceof SettlerCarrier)
            tileIndex = 11;
        if (settler instanceof SettlerBuilder)
            tileIndex = 16;
        tile = viewComponents.tileSet.getTile(tileIndex);
    }

}
