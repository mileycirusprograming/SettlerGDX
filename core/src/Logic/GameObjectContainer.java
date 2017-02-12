package Logic;

import Logic.GameObject.Building;
import Logic.GameObject.Resource;
import Logic.GameObject.Settler;

import java.util.*;

/**
 * Created by landfried on 03.02.17.
 */
public class GameObjectContainer {
    private List<Nation> nations;
    private List<Settler> settlers;
    private List<Building> buildings;
    private List<Resource> resources;

    private Map<Integer, List<Settler>> nationSettlers;
    private Map<Integer, List<Building>> nationBuildings;
    private Map<Integer, List<Resource>> nationResources;



    public GameObjectContainer() {
        nations = new ArrayList<>();
        settlers = new ArrayList<>();
        buildings = new ArrayList<>();
        resources = new ArrayList<>();
        nationSettlers = new HashMap<>();
        nationBuildings = new HashMap<>();
        nationResources = new HashMap<>();
    }


    public void updateNations() {
        for (Nation nation : nations) {
            nation.update();

        }
    }

    public void updateSettlers() {
        for (Settler settler : settlers)
            settler.update();
    }

    public void updateBuildings() {
        for (Building building : buildings)
            building.update();
    }

    private Nation getNationById(int id) {
        for (Nation nation : nations)
            if (nation.id == id)
                return nation;
        return null;
    }

    private void refreshNationObjects() {
        for (List<Settler> list : nationSettlers.values())
            list.clear();
        for (List<Building> list : nationBuildings.values())
            list.clear();
        for (List<Resource> list : nationResources.values())
            list.clear();

        for (Settler settler : settlers)
            nationSettlers.get(settler.getNationId()).add(settler);

        for (Building building : buildings)
            nationBuildings.get(building.getNationId()).add(building);

        for (Resource resource : resources)
            nationResources.get(resource.getNationId()).add(resource);

    }

    public void createNation() {
        int nationId = Nation.getNextId();
        Nation newNation = new Nation(nationId, new NationObjectAccessor(nationId, this));
        nations.add(newNation);
        nationSettlers.put(newNation.id, new ArrayList<Settler>());
        nationBuildings.put(newNation.id, new ArrayList<Building>());
        nationResources.put(newNation.id, new ArrayList<Resource>());
    }

    public void addSettler(Settler newSettler) {
        settlers.add(newSettler);
        refreshNationObjects();
    }

    public void addBuilding(Building newBuilding) {
        buildings.add(newBuilding);
        refreshNationObjects();
    }

    public void addResource(Resource resource) {
        resources.add(resource);
        refreshNationObjects();
    }







    // == Getter, Setter


    public List<Nation> getNations() {
        return nations;
    }

    public void setNations(List<Nation> nations) {
        this.nations = nations;
    }

    public List<Settler> getSettlers() {
        return settlers;
    }

    public void setSettlers(List<Settler> settlers) {
        this.settlers = settlers;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Building> getBuildings(int nationId) {
        return nationBuildings.get(nationId);
    }

    public List<Settler> getSettlers(int nationId) {
        return nationSettlers.get(nationId);
    }

    public List<Resource> getResources(int nationId) {
        return nationResources.get(nationId);
    }

}
