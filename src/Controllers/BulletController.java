package Controllers;

import Models.Bullet;
import Models.GameConfig;
import Models.GameObject;
import Views.GameDrawer;

/**
 * Created by Minh on 8/2/2016.
 */
public class BulletController extends SingleController{
    private static final int SPEED = 20;
    public BulletController(Bullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = -SPEED;
    }

    @Override
    public void run() {
        super.run();
        if(!GameConfig.getInst().isInScreen(this.gameObject)){
            this.gameObject.destroy();
        }
    }
}
