package Controllers.Plane;

import Controllers.*;
import Controllers.GameScenes.GameOverGameScene;
import Controllers.GameScenes.GameSceneListener;
import Controllers.GameScenes.PlayGameScene;
import Controllers.Gift.GiftSubscriber;
import Models.Bullet;
import Models.GameObject;
import Models.GameObjectWithHP;
import Models.Plane;
import Views.GameDrawer;
import Views.ImageDrawer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Minh on 8/2/2016.
 */
public class PlaneController extends SingleControllerWithHP implements KeyListener, Colliable, GiftSubscriber {
    public static final int PLANE_SPEED = 10;
    private static final int FIRE_BALL_PERIOD = 100;
    private static final int FIRE_BALL_RADIUS = 100;
    private int fireBallCount = 0;

    private PlaneState planeState;
    private ControllerManager bulletManager;
    private GameSceneListener gameSceneListener;
    private FireBallController fireBallController;

    private PlaneController(Plane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.bulletManager = new ControllerManager();
        planeState = PlaneState.NORMAL;
        CollisionPool.getInst().add(this);
        NotificationCenter.instance.subcribeFireBall(this);
    }

    public void setGameSceneListener(GameSceneListener gameSceneListener) {
        this.gameSceneListener = gameSceneListener;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                    this.gameVector.dx = -PLANE_SPEED;
                    break;
            case KeyEvent.VK_RIGHT:
                    this.gameVector.dx = PLANE_SPEED;
                    break;
            case KeyEvent.VK_UP:
                    this.gameVector.dy = -PLANE_SPEED;
                    break;
            case KeyEvent.VK_DOWN:
                    this.gameVector.dy = PLANE_SPEED;
                    break;
            case KeyEvent.VK_SPACE:
                    BulletController bulletController = new BulletController(new Bullet(this.gameObject.getMiddleX() - Bullet.WIDTH / 2 , this.gameObject.getY()), new ImageDrawer("resources/bullet.png"));
                    bulletManager.add(bulletController);
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    this.gameVector.dx = 0;
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    this.gameVector.dy = 0;
                    break;
            }
    }

    public void draw (Graphics g){
        if (this.gameObject.isAlive()){
            super.draw(g);
            bulletManager.draw(g);
        }
    }

    @Override
    public void run() {
        super.run();
        bulletManager.run();
        switch (planeState){
            case NORMAL:
                break;
            case FIREBALL:
                fireBallCount++;
                int x = PlaneController.instance.gameObject.getX() + PlaneController.instance.getGamObject().getWidth() / 2 - FIRE_BALL_RADIUS;
                int y = PlaneController.instance.gameObject.getY() + PlaneController.instance.getGamObject().getHeight() / 2;
                if (fireBallCount == FIRE_BALL_PERIOD){
                    planeState = PlaneState.NORMAL;
                }
                if ( fireBallCount == 1 ){
                    FireBallManager.getInst().add(FireBallController.create(x, y));
                }
                break;
        }
    }

    public final static PlaneController instance = new PlaneController(new Plane(300, 600), new ImageDrawer("resources/plane4.png"));

    @Override
    public void onCollide(Colliable c) {
    }

    public void decreaseHP(int amount){
        ((GameObjectWithHP)gameObject).decreaseHP(amount);
        if (((GameObjectWithHP) gameObject).getHp() <= 0){
            gameSceneListener.changeGameScene(new GameOverGameScene());
        }
    }

    @Override
    public void onFireBall(int x, int y) {
        planeState = PlaneState.FIREBALL;
        fireBallCount = 0;
    }

    @Override
    public GameObject getGamObject() {
        return gameObject;
    }
}
