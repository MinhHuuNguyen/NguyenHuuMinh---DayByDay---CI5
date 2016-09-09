package Controllers.Bomb;

import Controllers.*;
import Controllers.Enemy.SingleController;
import Controllers.Plane.PlaneController;
import Models.Bomb;
import Models.GameSetting;
import Models.GameObject;
import Views.GameDrawer;
import Views.ImageDrawer;

/**
 * Created by Minh on 8/9/2016.
 */
public class BombController2 extends SingleController implements Colliable {
    private static final int SPEED = 10;
    public BombController2(GameObject gameObject, GameDrawer gameDrawer) {
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

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController){
            NotificationCenter.instance.onBigBombExplode(gameObject.getX(), gameObject.getY());
            gameObject.destroy();
        }
    }
    public static BombController2 create(int x, int y, BombType bombType){
        BombController2 bombController2 = null;
        switch (bombType){
            case BIG:
                bombController2 = new BombController2(new Bomb(x, y), new ImageDrawer("resources/bomb_2.png"));
                break;
            case SMALL:
                bombController2 = new BombController2(new Bomb(x, y), new ImageDrawer("resources/bomb_1.png"));
                break;
        }
        return bombController2;
    }
}
