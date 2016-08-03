package Controllers;

import Models.EnemyBullet;
import Views.GameDrawer;

/**
 * Created by Minh on 8/3/2016.
 */
public class EnemyBulletController extends SingleController {
    public static final int SPEED = 10;
    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = SPEED;
    }
}
