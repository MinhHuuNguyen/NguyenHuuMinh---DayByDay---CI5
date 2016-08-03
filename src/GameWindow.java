import Controllers.EnemyController;
import Controllers.EnemyManager;
import Controllers.PlaneController;
import Models.GameConfig;
import Utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by Minh on 7/27/2016.
 */
public class GameWindow extends Frame implements Runnable{
    Image background;
//    PlaneController planeController1;
    BufferedImage bufferedImage;
    Graphics bufferedImageGraphics;
    Thread thread;
    GameConfig gameConfig;


    public GameWindow(){
        this.setVisible(true);
        this.gameConfig = GameConfig.getInst();
        this.setSize(gameConfig.getScreenWidth(), gameConfig.getScreenHeight());
        this.setLocation(0, 0);


        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
            background = Utils.loadImage("resources/background.png");

        this.addKeyListener(PlaneController.getPlaneController1());
//        this.addMouseMotionListener(new MouseMotionListener() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                plane2.moveTo(e.getX() - (planeWidth / 2), e.getY() - (planeHeight / 2));
//                Bullet bullet = new Bullet();
//                bullet.moveTo(plane2.x + (planeWidth / 2) - 5, plane2.y);
//                bulletVector.add(bullet);
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                plane2.moveTo(e.getX() - (planeWidth / 2), e.getY() - (planeHeight / 2));
//            }
//        });
//        this.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                Bullet bullet = new Bullet();
//                bullet.moveTo(plane2.x + (planeWidth / 2) - 5, plane2.y);
//                bulletVector.add(bullet);
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
        this.bufferedImage = new BufferedImage(600,800,BufferedImage.TYPE_3BYTE_BGR);
        this.bufferedImageGraphics = bufferedImage.getGraphics();
        thread = new Thread(this);
        thread.start();
    }
    @Override

    public void update(Graphics g){
        bufferedImageGraphics.drawImage(background,0,0,null);
        PlaneController.getPlaneController1().draw(bufferedImageGraphics);
        EnemyManager.getInst().draw(bufferedImageGraphics);
        g.drawImage(bufferedImage,0,0,null);
    }
    public void run(){
        while(true) {
            try {
                Thread.sleep(gameConfig.getThreadDelay());
                PlaneController.getPlaneController1().run();
                EnemyManager.getInst().run();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}