package View;

import Logic.GameObject.Settler;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by landfried on 03.03.17.
 */
public class SettlerSprite {
    Settler settler;
    TiledMapTile tile;
    LinkedList<TiledMapTileLayer.Cell> cells;
    TiledMapTileLayer layer;
    ViewComponents viewComponents;

    public SettlerSprite(Settler settler, ViewComponents viewComponents) {
        this.settler = settler;
        this.tile = viewComponents.tileSet.getTile(7);
        this.layer = viewComponents.getLayer(viewComponents.SETTLER_LAYER);
        cells = new LinkedList<>();
        this.viewComponents = viewComponents;
        putCell();
    }

    public void update() {
        if (settler.moved) {
            putCell();
            settler.moved = false;
        }

        emptyDirtyCells();
        removeEmptyCells();
    }

    private void putCell() {
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tile);
        cells.addFirst(cell);
        layer.setCell(settler.getPosition().x, settler.getPosition().y, cell);
    }

    private void emptyDirtyCells() {
        TiledMapTileLayer.Cell firstCell = cells.pop();
        for (TiledMapTileLayer.Cell cell : cells)
            cell.setTile(null);
        cells.addFirst(firstCell);
    }

    private void removeEmptyCells() {
        LinkedList<TiledMapTileLayer.Cell> newCells = new LinkedList<>();
        for (TiledMapTileLayer.Cell cell : cells)
            if (cell.getTile() != null)
                newCells.add(cell);
        cells = newCells;
    }
}
