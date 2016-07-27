import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Minh on 7/27/2016.
 */
public class GameWindow extends Frame implements Runnable{
    Image background;
    Image plane;
    Image plane1;
    int planex = 250;
    int planey = 600;
    int planex1 = 300;
    int planey1 = 650;
    BufferedImage bufferedImage;
    Graphics bufferedImageGraphics;
    Thread thread;
    public GameWindow(){
        this.setVisible(true);
        this.setSize(600, 800);
        this.setLocation(0, 0);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("WindowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("WindowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("WindowClosed");
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
            plane = ImageIO.read(new File("resources/plane2.png"));
            plane1 = ImageIO.read(new File("resources/plane1.png"));
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
                    case KeyEvent.VK_LEFT:
                        planex-=10;
                        break;
                    case KeyEvent.VK_RIGHT:
                        planex+=10;
                        break;
                    case KeyEvent.VK_UP:
                        planey-=10;
                        break;
                    case KeyEvent.VK_DOWN:
                        planey+=10;
                        break;
                }
                //repaint();
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                planex1 = e.getX();
                planey1 = e.getY();
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
        bufferedImageGraphics.drawImage(plane,planex,planey,null);
        bufferedImageGraphics.drawImage(plane1,planex1,planey1,null);
        g.drawImage(bufferedImage,0,0,null);
    }
    public void run(){
        while(true){
            try {
                Thread.sleep(27);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
