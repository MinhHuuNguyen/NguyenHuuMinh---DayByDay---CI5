package Controllers.Bomb;

import Models.GameObject;

/**
 * Created by Minh on 8/12/2016.
 */
public interface BigBombSubscriber {
    public void onBigBombExplode(int x, int y);
    public GameObject getGamObject();
}
