package Controllers;

import Models.Bullet;
import Models.Enemy;
import Models.GameConfig;
import Models.GameObject;
import Views.GameDrawer;

/**
 * Created by Minh on 8/2/2016.
 */
public class BulletController extends SingleController implements Colliable{
    private static final int SPEED = 20;
    public BulletController(Bullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = -SPEED;
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
        if (c instanceof EnemyController){
            Enemy enemy = (Enemy) c.getGameObject();
            enemy.decreaseHP();
            if(((Enemy) c.getGameObject()).getHp() <= 0){
                c.getGameObject().destroy();
            }
            gameObject.destroy();
        }
        if (c instanceof EnemyController2){
            c.getGameObject().destroy();
            gameObject.destroy();
        }
        if (c instanceof EnemyBulletController){
            c.getGameObject().destroy();
            gameObject.destroy();
        }
    }
}
