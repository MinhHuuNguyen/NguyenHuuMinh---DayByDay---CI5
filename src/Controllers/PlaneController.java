package Controllers;

import Models.Bullet;
import Models.GameConfig;
import Models.GameObject;
import Models.Plane;
import Utils.Utils;
import Views.GameDrawer;
import Views.ImageDrawer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Minh on 8/2/2016.
 */
public class PlaneController extends SingleControllerWithHP implements KeyListener, Colliable{
    public static final int SPEED = 10;

    private ControllerManager bulletManager;

    private PlaneController(Plane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.bulletManager = new ControllerManager();
        CollisionPool.getInst().add(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                    this.gameVector.dx = -SPEED;
                    break;
            case KeyEvent.VK_RIGHT:
                    this.gameVector.dx = SPEED;
                    break;
            case KeyEvent.VK_UP:
                    this.gameVector.dy = -SPEED;
                    break;
            case KeyEvent.VK_DOWN:
                    this.gameVector.dy = SPEED;
                    break;
            case KeyEvent.VK_SPACE:
                    BulletController bulletController = new BulletController(new Bullet(this.gameObject.middleX() - Bullet.WIDTH / 2 , this.gameObject.getY()), new ImageDrawer("resources/bullet.png"));
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

    }

    public static PlaneController planeController1;

    public static PlaneController getPlaneController1(){
        if (planeController1 == null) {
            planeController1 = new PlaneController(new Plane(300, 600), new ImageDrawer("resources/plane4.png"));
        }
        return planeController1;
    }

    @Override
    public void onCollide(Colliable c) {
    }
}
