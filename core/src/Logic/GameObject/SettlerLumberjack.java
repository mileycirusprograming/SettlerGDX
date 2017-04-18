package Logic.GameObject;

import Logic.Mission.Mission;

/**
 * Created by Franken on 13.04.2017.
 */
public class SettlerLumberjack extends Settler {
    private enum State {WAITING, DEST_TREE, CUT_TREE, RETURN_TRUNK};
    private State state;

    public SettlerLumberjack() {super();}

    private void updateState(){
        switch (state){
            case WAITING:

                break;
            case DEST_TREE:
                break;
            case CUT_TREE:
                break;
            case RETURN_TRUNK:
                break;

        }
    }

    @Override
    public void update() {

    }

    @Override
    protected boolean isCorrectMission(Mission mission) {
        return false;
    }

    @Override
    protected void initMission() {

    }
}
