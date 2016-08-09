package Controllers;

import Models.*;
import Views.GameDrawer;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by Minh on 8/9/2016.
 */
public class BombController extends SingleController implements Colliable {
    private static final int SPEED = 5;
    private static final int BOMB_RADIUS = 200;
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
            gameObject.destroy();
            Iterator<SingleController> singleControllerIterator = EnemyManager.inst.singleControllerVector.iterator();
            while (singleControllerIterator.hasNext()) {
                SingleController enemyController = singleControllerIterator.next();
                int bombCenterX = this.gameObject.getX() + this.gameObject.getWidth() / 2;
                int bombCenterY = this.gameObject.getY() + this.gameObject.getHeight() / 2;
                int enemyCenterX = enemyController.gameObject.getX() +enemyController.gameObject.getWidth() / 2;
                int enemyCenterY = enemyController.gameObject.getY() +enemyController.gameObject.getHeight() / 2;
                double R = Math.sqrt((bombCenterX - enemyCenterX) * (bombCenterX - enemyCenterX) + (bombCenterY - enemyCenterY) * (bombCenterY - enemyCenterY));
                if(R <= BOMB_RADIUS) {
                    enemyController.gameObject.destroy();
                }
            }
        }
    }
}
