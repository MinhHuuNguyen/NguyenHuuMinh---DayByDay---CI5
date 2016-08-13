package Controllers.Lock;

import Models.GameObject;

/**
 * Created by Minh on 8/12/2016.
 */
public interface LockSubscriber {
    public void onFreeze(int x, int y);
    public GameObject getGamObject();
}
