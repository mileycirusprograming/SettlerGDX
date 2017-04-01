package View;

import Logic.GameObject.Resource;
import Logic.GameObject.ResourceType;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by landfried on 12.03.17.
 */
public class ResourceView {
    private ViewComponents viewComponents;

    private ArrayList<TiledMapTileLayer.Cell> cells;
    private TiledMapTileLayer layer;
    private static final HashMap<ResourceType, Integer> tileIndexMap;
    static
    {
        tileIndexMap = new HashMap<>();
        tileIndexMap.put(ResourceType.WOOD, 12);
        tileIndexMap.put(ResourceType.STONE, 13);

    }
    private HashMap<ResourceType, TiledMapTile> tileMap;


    private List<Resource> resources;


    public ResourceView(ViewComponents viewComponents) {
        this.viewComponents = viewComponents;
        cells = new ArrayList<>();
        layer = viewComponents.getLayer(viewComponents.RESOURCE_LAYER);
        resources = viewComponents.gameObjectContainer.getResources();
        tileMap = new HashMap<>();
        for (ResourceType resourceType : tileIndexMap.keySet())
            tileMap.put(resourceType, viewComponents.tileSet.getTile(tileIndexMap.get(resourceType)));

    }


    public void update() {
        clearCells();
        drawAllResources();
    }


    private void putCell(Resource resource) {
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tileMap.get(resource.getType()));
        cells.add(cell);
        layer.setCell(resource.getPosition().x, resource.getPosition().y, cell);
    }

    private void clearCells() {
        for (TiledMapTileLayer.Cell cell : cells)
            cell.setTile(null);
        cells.clear();
    }

    private void drawAllResources() {
        for (Resource resource : resources)
            //if (!resource.picked)
                putCell(resource);
    }


}
