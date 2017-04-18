package Logic.GameObject;

/**
 * Created by Franken on 13.04.2017.
 * ich glaube generell soltlen wir darüber nachdenken nicht alle die ganze zeit zu updaten
 * sondern mehr auf notifications oder events zurückgreifen
 */
public class BuildingLumberjack extends Building {


    public BuildingLumberjack() {

        constructionResources.put(ResourceType.STONE, 4);
        constructionResources.put(ResourceType.WOOD, 1);

    }


    @Override
    public void update() {
        switch (state){
            case BUILT:

                break;
            case CONSTRUCT:
                break;
            case PRODUCTION:
                break;
            case SLEEP:
                break;
        }

    }
}
