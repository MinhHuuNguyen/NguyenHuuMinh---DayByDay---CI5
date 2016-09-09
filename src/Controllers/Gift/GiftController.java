package Controllers.Gift;

import Controllers.Colliable;
import Controllers.CollisionPool;
import Controllers.Enemy.SingleController;
import Controllers.NotificationCenter;
import Controllers.Plane.PlaneController;
import Models.GameSetting;
import Models.Gift;
import Views.GameDrawer;
import Views.ImageDrawer;

/**
 * Created by Minh on 8/20/2016.
 */
public class GiftController extends SingleController implements Colliable {
    private static final int SPEED = 5;
    public GiftController(Gift gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = SPEED;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        if(!GameSetting.getInstance().isInScreen(this.gameObject)){
            this.gameObject.destroy();
        }
    }
    public static GiftController create(int x, int y){
        return new GiftController(new Gift(x, y), new ImageDrawer("resources/gift.png"));
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController){
            NotificationCenter.instance.onFireBall(gameObject.getX(), gameObject.getY());
            gameObject.destroy();
        }
    }
}
