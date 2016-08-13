package Controllers.Bomb;

import Controllers.*;
import Controllers.Enemy.SingleController;
import Controllers.Plane.PlaneController;
import Models.*;
import Views.GameDrawer;
import Views.ImageDrawer;

/**
 * Created by Minh on 8/9/2016.
 */
public class BombController extends SingleController implements Colliable {
    private static final int SPEED = 5;
    public BombController(Bomb gameObject, GameDrawer gameDrawer) {
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
            NotificationCenter.instance.onSmallBombExplode(gameObject.getX(), gameObject.getY());
            gameObject.destroy();
        }
    }
    public static BombController create(int x, int y, BombType bombType){
        BombController bombController = null;
        switch (bombType){
            case BIG:
                bombController = new BombController(new Bomb(x, y), new ImageDrawer("resources/bomb_2.png"));
                break;
            case SMALL:
                bombController = new BombController(new Bomb(x, y), new ImageDrawer("resources/bomb_1.png"));
                break;
        }
        return bombController;
    }

    @Override
    public GameObject getGameObject() {
        return super.getGameObject();
    }
}
