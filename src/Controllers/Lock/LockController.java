package Controllers.Lock;

import Controllers.Colliable;
import Controllers.CollisionPool;
import Controllers.Enemy.SingleController;
import Controllers.NotificationCenter;
import Controllers.Plane.PlaneController;
import Models.GameConfig;
import Models.GameObject;
import Models.Lock;
import Views.GameDrawer;
import Views.ImageDrawer;

/**
 * Created by Minh on 8/12/2016.
 */
public class LockController extends SingleController implements Colliable {
    private static final int SPEED = 5;
    public LockController(Lock gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = SPEED;
        CollisionPool.getInst().add(this);
    }

    public static LockController create(int x, int y){
        return new LockController(new Lock(x, y), new ImageDrawer("resources/lock.png"));
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
            NotificationCenter.instance.onFreeze(gameObject.getX(), gameObject.getY());
            gameObject.destroy();
        }

    }
    @Override
    public GameObject getGameObject() {
        return super.getGameObject();
    }
}
