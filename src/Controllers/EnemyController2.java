package Controllers;

import Models.Enemy;
import Models.EnemyBullet;
import Models.GameConfig;
import Models.Plane;
import Views.GameDrawer;
import Views.ImageDrawer;

import java.awt.*;

/**
 * Created by Minh on 8/3/2016.
 */
public class EnemyController2 extends SingleControllerWithHP implements Colliable{
    private EnemyBulletManager enemyBulletManager;
    private int count = 0;

    public static final int SPEED = 1;
    public EnemyController2(Enemy gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dx = SPEED;
        enemyBulletManager = new EnemyBulletManager();
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        this.enemyBulletManager.run();
        count++;
        if (GameConfig.getInst().durationInSeconds(count) >= 1){
            count = 0;
            EnemyBullet enemyBullet = new EnemyBullet(gameObject.getX() + gameObject.getWidth() / 2 - EnemyBullet.WIDTH / 2, gameObject.getY() +gameObject.getHeight(), EnemyBullet.WIDTH, EnemyBullet.HEIGHT);
            ImageDrawer imageDrawer= new ImageDrawer("resources/enemy_bullet.png");
            EnemyBulletController enemyBulletController = new EnemyBulletController(enemyBullet, imageDrawer);
            this.enemyBulletManager.add(enemyBulletController);
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        this.enemyBulletManager.draw(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController){
            Plane plane = (Plane) c.getGameObject();
            plane.decreaseHP(10);
            if(((Plane) c.getGameObject()).getHp() <= 0){
                c.getGameObject().destroy();
            }
            gameObject.destroy();
        }
    }
}
