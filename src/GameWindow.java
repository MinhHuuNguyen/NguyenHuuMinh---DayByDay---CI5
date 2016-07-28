import Models.Bullet;
import Models.EnemyPlane;
import Models.Plane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Minh on 7/27/2016.
 */
public class GameWindow extends Frame implements Runnable{
    Image background;
    Image plane1image;
    Image plane2image;
    Image bulletimage;
    Image enemyPlaneImage;

    int planeWidth;
    int planeHeight;
    int enemyPlaneX;
    int enemyPlaneY;
    int delay;
    int enemyPlaneImageWidth;
    int enemyPlaneImageHeight;

    Plane plane1;
    Plane plane2;

    Random rand = new Random();

    Vector<Bullet> bulletVector;
    Vector<EnemyPlane> enemyPlaneVector;

    BufferedImage bufferedImage;
    Graphics bufferedImageGraphics;
    Thread thread;
    Thread thread1;
    public GameWindow(){
        this.setVisible(true);
        this.setSize(600, 800);
        this.setLocation(0, 0);

        plane1 = new Plane(250, 600);
        plane2 = new Plane(300, 600);

        delay = rand.nextInt(599) + 2;

        bulletVector = new Vector<>();
        enemyPlaneVector = new Vector<>();

        for(int i = 0; i <= 1000; i++) {
            enemyPlaneX = rand.nextInt(599) + 2;
            enemyPlaneY = rand.nextInt(100000) + 0;
            EnemyPlane enemyPlane = new EnemyPlane();
            enemyPlane.moveTo(enemyPlaneX, -enemyPlaneY);
            enemyPlaneVector.add(enemyPlane);
        }

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
        try {
            background = ImageIO.read(new File("resources/background.png"));
            plane1image = ImageIO.read(new File("resources/plane2.png"));
            plane2image = ImageIO.read(new File("resources/plane3.png"));
            bulletimage = ImageIO.read(new File("resources/bullet.png"));
            enemyPlaneImage = ImageIO.read(new File("resources/enemy_plane_white_1.png"));
            enemyPlaneImageWidth = enemyPlaneImage.getWidth(null);
            enemyPlaneImageHeight = enemyPlaneImage.getHeight(null);
            planeWidth = plane2image.getWidth(null);
            planeHeight = plane2image.getHeight(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        Bullet bullet = new Bullet();
                        bullet.moveTo(plane1.x + (planeWidth / 2) - 5, plane1.y);
                        bulletVector.add(bullet);
                        break;
                    case KeyEvent.VK_LEFT:
                        plane1.x -= 10;
                        break;
                    case KeyEvent.VK_RIGHT:
                        plane1.x += 10;
                        break;
                    case KeyEvent.VK_UP:
                        plane1.y -= 10;
                        break;
                    case KeyEvent.VK_DOWN:
                        plane1.y += 10;
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                plane2.moveTo(e.getX() - (planeWidth / 2), e.getY() - (planeHeight / 2));
                Bullet bullet = new Bullet();
                bullet.moveTo(plane2.x + (planeWidth / 2) - 5, plane2.y);
                bulletVector.add(bullet);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                plane2.moveTo(e.getX() - (planeWidth / 2), e.getY() - (planeHeight / 2));
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Bullet bullet = new Bullet();
                bullet.moveTo(plane2.x + (planeWidth / 2) - 5, plane2.y);
                bulletVector.add(bullet);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.bufferedImage = new BufferedImage(600,800,BufferedImage.TYPE_3BYTE_BGR);
        this.bufferedImageGraphics = bufferedImage.getGraphics();
        thread = new Thread(this);
        thread.start();
    }
    @Override

    public void update(Graphics g){
        bufferedImageGraphics.drawImage(background,0,0,null);
        bufferedImageGraphics.drawImage(plane1image, plane1.x, plane1.y, null);
        bufferedImageGraphics.drawImage(plane2image, plane2.x, plane2.y, null);
        for(EnemyPlane enemyPlane : enemyPlaneVector) {
            bufferedImageGraphics.drawImage(enemyPlaneImage, enemyPlane.x, enemyPlane.y , null);
        }
        for(Bullet bullet : bulletVector) {
            bufferedImageGraphics.drawImage(bulletimage, bullet.x, bullet.y , null);
        }
        g.drawImage(bufferedImage,0,0,null);
    }
    public void run(){
        while(true) {
            try {
                Thread.sleep(27);
                Iterator<Bullet> bulletIterator = bulletVector.iterator();
                Iterator<EnemyPlane> enemyPlaneIterator = enemyPlaneVector.iterator();
                //Bullet bullet = bulletIterator.next();
                while(bulletIterator.hasNext()){
                    Bullet bullet = bulletIterator.next();
                    bullet.y -= 10;
                    if(bullet.y <= 0) {
                        bulletIterator.remove();
                    }
                    while(enemyPlaneIterator.hasNext()){
                        EnemyPlane enemyPlane = enemyPlaneIterator.next();
                        enemyPlane.y += 5;
                        if(enemyPlane.y >= 800 ) {
                            enemyPlaneIterator.remove();
                        }
                        if(bullet.y <= enemyPlane.y + enemyPlaneImageHeight && bullet.y >= enemyPlane.y - enemyPlaneImageHeight && bullet.x >= enemyPlane.x - enemyPlaneImageWidth && bullet.x <= enemyPlane.x + enemyPlaneImageWidth ) {
                        enemyPlaneIterator.remove();
                        bulletIterator.remove();
                        }
                    }
                }
                while(enemyPlaneIterator.hasNext()){
                    EnemyPlane enemyPlane = enemyPlaneIterator.next();
                    enemyPlane.y += 5;
                    if(enemyPlane.y >= 800 ) {
                        enemyPlaneIterator.remove();
                    }
                }
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}