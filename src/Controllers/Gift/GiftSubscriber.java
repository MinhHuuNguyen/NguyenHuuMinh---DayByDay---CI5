package Controllers.Gift;

import Models.GameObject;

/**
 * Created by Minh on 8/20/2016.
 */
public interface GiftSubscriber {
    void onFireBall(int x, int y);
    GameObject getGamObject();
}