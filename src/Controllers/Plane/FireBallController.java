package Controllers.Plane;

import Controllers.Colliable;
import Controllers.CollisionPool;
import Controllers.Enemy.EnemyController;
import Controllers.Enemy.SingleController;
import Models.FireBall;
import Models.GameObject;
import Models.Plane;
import Views.GameDrawer;
import Views.ImageDrawer;

import java.awt.*;
import java.util.Random;

/**
 * Created by Minh on 8/20/2016.
 */
public class FireBallController extends SingleController implements Colliable {
    private static double RAD = 0;
    private static final int FIRE_BALL_RADIUS = 100;
    private static final int FIRE_BALL_SPEED = 5;
    private static int count = 0;
    private static final int FIRE_BALL_PERIOD = 100;
    private int fireBallCount = 0;
    public FireBallController(FireBall gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
//        this.gameVector.dx = (int)(PlaneController.instance.getGamObject().getMiddleX() - FIRE_BALL_RADIUS * Math.cos(RAD));
//        this.gameVector.dy = (int)(PlaneController.instance.getGamObject().getMiddleY() - FIRE_BALL_RADIUS * Math.sin(RAD));
        Random rand = new Random();
        int a = rand.nextInt(3) + 1;
        if (a == 1){
            this.gameVector.dx = FIRE_BALL_SPEED;
        }else if (a == 2){
            this.gameVector.dy = -FIRE_BALL_SPEED;
        }else {
            this.gameVector.dx = -FIRE_BALL_SPEED;
        }
        CollisionPool.getInst().add(this);
    }

    public static FireBallController create(int x, int y){
        return new FireBallController(new FireBall(x, y), new ImageDrawer("resources/fireball.png"));
    }

//    @Override
//    public void run() {
//        super.run();
//        count++;
//        fireBallCount++;
//        if (count == FIRE_BALL_SPEED) {
//            count = 0;
//            this.getGameObject().moveTo(
//                    (int) (PlaneController.instance.getGameObject().getMiddleX() - FIRE_BALL_RADIUS * Math.cos(RAD)),
//                    (int) (PlaneController.instance.getGameObject().getMiddleY() - FIRE_BALL_RADIUS * Math.sin(RAD))
//            );
//            RAD++;
//            if (RAD == 360) {
//                RAD = 0;
//            }
//        }
//        if (fireBallCount == FIRE_BALL_PERIOD) {
//            this.getGameObject().destroy();
//        }
//    }

    @Override
    public void onCollide(Colliable c) {
    }

}
