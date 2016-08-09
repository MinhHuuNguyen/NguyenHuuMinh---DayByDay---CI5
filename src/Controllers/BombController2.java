package Controllers;

import Models.Enemy;
import Models.GameConfig;
import Models.GameObject;
import Models.GameObjectWithHP;
import Views.GameDrawer;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by Minh on 8/9/2016.
 */
public class BombController2 extends SingleController implements Colliable {
    private static final int SPEED = 10;
    public BombController2(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = SPEED;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        if(!GameConfig.getInst().isInScreen(this.gameObject)){
            this.gameObject.destroy();
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController){
            this.gameObject.destroy();
            Iterator<SingleController> singleControllerIterator = EnemyManager.inst.singleControllerVector.iterator();
            while (singleControllerIterator.hasNext()) {
                SingleController enemyController = singleControllerIterator.next();
                enemyController.gameObject.destroy();
            }
        }
    }
}
