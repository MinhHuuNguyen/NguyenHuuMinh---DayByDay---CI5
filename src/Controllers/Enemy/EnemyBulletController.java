package Controllers.Enemy;

import Controllers.Colliable;
import Controllers.CollisionPool;
import Controllers.GameScenes.*;
import Controllers.Plane.PlaneController;
import Models.EnemyBullet;
import Models.GameObjectWithHP;
import Models.Plane;
import Views.GameDrawer;

/**
 * Created by Minh on 8/3/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable {
    public static final int SPEED = 10;
    GameSceneListener gameSceneListener;
    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
//        this.gameVector.dy = SPEED;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController){
            PlaneController.instance.decreaseHP(10);
            this.gameObject.destroy();
        }
    }
}
