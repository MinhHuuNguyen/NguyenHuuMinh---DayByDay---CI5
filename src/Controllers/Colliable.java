package Controllers;

import Models.GameObject;

/**
 * Created by Minh on 8/9/2016.
 */
public interface Colliable {
    void onCollide (Colliable c);
    GameObject getGameObject();

}
