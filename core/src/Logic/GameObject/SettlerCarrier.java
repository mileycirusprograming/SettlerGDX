package Logic.GameObject;

import Logic.Mission.Mission;
import Logic.Mission.MissionCarrier;

/**
 * Created by landfried on 30.01.17.
 */
public class SettlerCarrier extends Settler {
    private ObjectPosition destination;
    private final ObjectPosition direction = new ObjectPosition();

    public SettlerCarrier() {
        super();
    }

    private ObjectPosition getDestination() {
        ObjectPosition destination = new ObjectPosition();
        if (getMission() != null)
            destination = getMission().getBuilding().getPosition();
        return destination;
    }

    private MissionCarrier getMissionCarrier() {
        return (MissionCarrier) getMission();
    }

    @Override
    public void update() {
        destination = getDestination();
        int deltaX = destination.x - getPosition().x;
        int deltaY = destination.y - getPosition().y;
        direction.x = deltaX;
        direction.y = deltaY;
    }

    // sollten nciht sowieso nur SettlerCarrier MissionCarriers bekommen und für alles andere genau so?
    @Override
    public boolean isCorrectMission(Mission mission) {
        return (mission instanceof MissionCarrier);
    }

    public ObjectPosition getDirection() {
        return direction;
    }
}