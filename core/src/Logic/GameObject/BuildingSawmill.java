package Logic.GameObject;


/**
 * Created by landfried on 15.04.17.
 */
public class BuildingSawmill extends Building {
    public BuildingSawmill() {

        constructionResources.put(ResourceType.STONE, 3);
        constructionResources.put(ResourceType.WOOD, 2);

        productionResources.add(ResourceType.WOOD);
    }

    @Override
    protected void produce() {
        Resource wood = getStoredResource(ResourceType.WOOD);
        if (wood == null)
            return;

        wood.process();
        Resource plank = new Resource(ResourceType.PLANK);
        plank.setPosition(getPosition());
        producedResources.add(plank);
    }


}
