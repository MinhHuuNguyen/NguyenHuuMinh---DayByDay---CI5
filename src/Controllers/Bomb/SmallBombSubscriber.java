package Controllers.Bomb;

import Models.GameObject;

/**
 * Created by Minh on 8/12/2016.
 */
public interface SmallBombSubscriber {
    public void onSmallBombExplode(int x, int y);
    public GameObject getGamObject();
}
