package Logic.GameObject;

import Logic.Mission.Mission;

/**
 * Created by landfried on 30.01.17.
 */
public abstract class Settler extends GameObject {
    private boolean busy;
    private Mission mission;
    public boolean moved;
    public long nextMoveTime;

    public Settler() {
        super();
        busy = false;
        moved = false;
        nextMoveTime = System.currentTimeMillis();

    }

    public abstract void update();

    protected abstract boolean isCorrectMission(Mission mission);

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public boolean setMission(Mission mission) {
        if (!isCorrectMission(mission)) {
            mission.abort();
            return false;
        }

        if (this.mission != null)
            this.mission.abort();

        this.mission = mission;
        setBusy(true);
        this.mission.begin();

        return true;
    }

    public Mission getMission() {
        return mission;
    }

    public void move(ObjectPosition delta) {
        getPosition().x += delta.x;
        getPosition().y += delta.y;
        moved = true;
    }
}
