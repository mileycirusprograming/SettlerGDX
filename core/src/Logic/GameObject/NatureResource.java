package Logic.GameObject;



/**
 * Created by Franken on 13.04.2017.
 */
public class NatureResource extends GameObject {

    private ResourceType gainingResource;
    private int mineTime;
    private NatureResourceType type;

    public NatureResource(NatureResourceType type){
        this.type = type;

        switch(type){
            case TREE:
                this.mineTime = 7;
                this.gainingResource = Logic.GameObject.ResourceType.TRUNK;
                break;
        }
    }

    public synchronized void mine (){
        if (mineTime > 0) {
            mineTime--;
            if (mineTime <= 0) {
                //deleteme
                getMined(this.getPosition());
            }
        }
    }

    private void getMined(ObjectPosition position){
        Resource resource = new Resource(this.gainingResource);
        resource.setPosition(position);
    }

    public int getMineTime() {
        return mineTime;
    }


}
