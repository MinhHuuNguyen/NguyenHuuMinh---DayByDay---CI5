package Controllers;

import Models.Enemy;
import Models.EnemyBullet;
import Models.GameConfig;
import Views.GameDrawer;
import Views.ImageDrawer;

import java.awt.*;

/**
 * Created by Minh on 8/3/2016.
 */
public class EnemyController2 extends SingleController {
    private EnemyBulletManager enemyBulletManager;
    private int count = 0;

    public static final int SPEED = 1;
    public EnemyController2(Enemy gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dx = SPEED;
        enemyBulletManager = new EnemyBulletManager();
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
}
