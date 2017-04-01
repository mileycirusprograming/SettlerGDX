package View;

import Logic.GameObject.Building;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landfried on 19.03.17.
 */
public class BuildingView {
    private ViewComponents viewComponents;

    private TiledMapTile tile;
    private final int xWidth = 2;
    private final int yWidth = 2;
    private TiledMapTile[][] tiles;
    private ArrayList<TiledMapTileLayer.Cell> cells;
    private TiledMapTileLayer layer;
    private final int tileIndex = 7;
    private List<Building> buildings;


    public BuildingView(ViewComponents viewComponents) {
        this.viewComponents = viewComponents;
        tile = viewComponents.tileSet.getTile(tileIndex);
        tiles = new TiledMapTile[xWidth][yWidth];
        cells = new ArrayList<>();
        layer = viewComponents.getLayer(viewComponents.BUILDING_LAYER);
        buildings = viewComponents.gameObjectContainer.getBuildings();

        setTiles();
    }

    public void update() {
        clearCells();
        drawAllBuildings();
    }

 private void setTiles() {
     tiles[0][1] = viewComponents.tileSet.getTile(4);
     tiles[1][1] = viewComponents.tileSet.getTile(5);
     tiles[0][0] = viewComponents.tileSet.getTile(9);
     tiles[1][0] = viewComponents.tileSet.getTile(10);
 }


    private void putCells(Building building) {
        for (int x=0; x<xWidth; x++) {
            for (int y=0; y<yWidth; y++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(tiles[x][y]);
                cells.add(cell);
                layer.setCell(building.getPosition().x + x, building.getPosition().y + y, cell);
            }
        }
    }

    private void clearCells() {
        for (TiledMapTileLayer.Cell cell : cells)
            cell.setTile(null);
        cells.clear();
    }

    private void drawAllBuildings() {
        for (Building building : buildings)
            putCells(building);
    }

}
