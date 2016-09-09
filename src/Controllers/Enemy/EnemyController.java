package Controllers.Enemy;

import Controllers.*;
import Controllers.Bomb.BigBombSubscriber;
import Controllers.Bomb.BombManager;
import Controllers.Bomb.SmallBombSubscriber;
import Controllers.GameScenes.GameOverGameScene;
import Controllers.GameScenes.GameSceneListener;
import Controllers.Lock.LockSubscriber;
import Controllers.Plane.FireBallController;
import Controllers.Plane.PlaneController;
import Models.*;
import Views.GameDrawer;
import Views.ImageDrawer;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Minh on 8/2/2016.
 */
public class EnemyController extends SingleControllerWithHP implements Colliable, BigBombSubscriber,SmallBombSubscriber, LockSubscriber {
    private EnemyBulletManager enemyBulletManager;
    private int count = 0;

    private static final int SPEED = 3;
    private static final int BULLET_SPEED = 12;
    private static final int BOMB_RADIUS = 300;
    private static final int FREEZED_PERIOD = 300;
    private int lockCount;

    private EnemyState enemyState;
    private static EnemyBulletType enemyBulletType;

    public EnemyController(Enemy gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        enemyBulletManager = new EnemyBulletManager();
        enemyState = EnemyState.NORMAL;
        CollisionPool.getInst().add(this);
        NotificationCenter.instance.subscribeBigBomb(this);
        NotificationCenter.instance.subscribeSmallBomb(this);
        NotificationCenter.instance.subscribeLock(this);
    }

    @Override
    public void run() {
        count++;
        switch (enemyState){
            case NORMAL:
                super.run();
                if (!GameSetting.getInstance().isInScreen(gameObject)){
                    gameObject.destroy();
                }
                if (GameSetting.getInstance().toSeconds(count) > 1.0) {
                    count = 0;
                    EnemyBulletController enemyBulletController = new EnemyBulletController(new EnemyBullet(this.gameObject.getMiddleX() - EnemyBullet.WIDTH / 2, this.gameObject.getBottom()),
                            new ImageDrawer("resources/enemy_bullet.png"));
                    switch (enemyBulletType){
                        case STRAIGHT:
                            enemyBulletController.gameVector.dy = BULLET_SPEED;
                            break;
                        case FOLLOWED:
                            int dx = (PlaneController.instance.getGameObject().getX() + PlaneController.instance.getGameObject().getWidth() / 2) - (gameObject.getX() + gameObject.getWidth() / 2);
                            int dy = (PlaneController.instance.getGameObject().getY() + PlaneController.instance.getGameObject().getHeight() / 2) - (gameObject.getY() + gameObject.getHeight());
                            if (dy > 0){
                                double ratio = Math.sqrt(dx * dx + dy * dy) / BULLET_SPEED;
                                enemyBulletController.gameVector.dy = (int)(dy / ratio);
                                enemyBulletController.gameVector.dx = (int)(dx / ratio);
                            }
                            break;
                    }
                    EnemyBulletManager.enemyBulletManager.add(enemyBulletController);
                }
                break;
            case FREEZED:
                lockCount++;
                if (lockCount == FREEZED_PERIOD){
                    enemyState = EnemyState.NORMAL;
                }
                break;
        }
//        if (this.enemyMovement == EnemyMovement.ZIKZAK){
//            if(count == 2){
//                this.gameVector.dx = -this.gameVector.dx;
//            }
//        }

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController){
            PlaneController.instance.decreaseHP(20);
            gameObject.destroy();
        }
        if (c instanceof FireBallController){
            gameObject.destroy();
        }

    }
    public static EnemyController create(int x, int y, EnemyType type, EnemyMovement movement, EnemyShot shot){
        EnemyController enemyController = null;
        switch (type){
            case YELLOW:
                enemyController = new EnemyController(new Enemy(x, y), new ImageDrawer("resources/enemy_plane_yellow_1.png"));
                break;
            case WHITE:
                enemyController = new EnemyController(new Enemy(x, y), new ImageDrawer("resources/enemy_plane_white_1.png"));
                break;
        }
        switch (movement){
            case STRAIGHT:
                enemyController.gameVector.dy = SPEED;
                break;
            case ZIKZAK:
                    enemyController.gameVector.dx = SPEED;
                    enemyController.gameVector.dy = SPEED;
                break;
            case CROSS:
                Random rand = new Random();
                int m2 = rand.nextInt(2) + 1;
                if ( m2 == 1){
                    enemyController.gameVector.dx = SPEED;
                    enemyController.gameVector.dy = SPEED;
                }else{
                    enemyController.gameVector.dx = -SPEED;
                    enemyController.gameVector.dy = SPEED;
                }
                break;
        }
        switch (shot){
            case STRAIGHT:
                enemyBulletType = EnemyBulletType.STRAIGHT;
                break;
            case FOLLOWED:
                enemyBulletType = EnemyBulletType.FOLLOWED;
                break;
        }
        return enemyController;
    }

    @Override
    public void onBigBombExplode(int x, int y) {
        gameObject.destroy();
    }

    @Override
    public void onFreeze(int x, int y) {
        enemyState = EnemyState.FREEZED;
        lockCount = 0;
    }

    @Override
    public GameObject getGamObject() {
        return gameObject;
    }

    @Override
    public void onSmallBombExplode(int x, int y) {
        Iterator<SingleController> singleControllerIterator = BombManager.inst.singleControllerVector.iterator();
        while (singleControllerIterator.hasNext()) {
            SingleController bombController = singleControllerIterator.next();
            int bombCenterX = bombController.gameObject.getX() + bombController.gameObject.getWidth() / 2;
            int bombCenterY = bombController.gameObject.getY() + bombController.gameObject.getHeight() / 2;
            int enemyCenterX = this.gameObject.getX() + this.gameObject.getWidth() / 2;
            int enemyCenterY = this.gameObject.getY() + this.gameObject.getHeight() / 2;
            double R = Math.sqrt((bombCenterX - enemyCenterX) * (bombCenterX - enemyCenterX) + (bombCenterY - enemyCenterY) * (bombCenterY - enemyCenterY));
            if (R <= BOMB_RADIUS){
                gameObject.destroy();
            }
        }
    }
}
