package Controllers;

import Models.GameObject;
import Models.GameObjectWithHP;
import Views.GameDrawer;

/**
 * Created by Minh on 8/9/2016.
 */
public class SingleControllerWithHP extends SingleController {
    public SingleControllerWithHP(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }
    public GameObjectWithHP gameObjectWithHP(){
        return  gameObjectWithHP();
    }
}
