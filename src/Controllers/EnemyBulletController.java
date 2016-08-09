package Controllers;

import Models.EnemyBullet;
import Models.Plane;
import Views.GameDrawer;

/**
 * Created by Minh on 8/3/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable {
    public static final int SPEED = 10;
    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = SPEED;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController){
            Plane plane = (Plane) c.getGameObject();
            plane.decreaseHP(1);
            if(((Plane) c.getGameObject()).getHp() <= 0){
                c.getGameObject().destroy();
            }
            gameObject.destroy();
        }
    }
}
