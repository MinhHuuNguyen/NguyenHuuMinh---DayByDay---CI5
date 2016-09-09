import Controllers.GameScenes.GameScene;
import Controllers.GameScenes.GameSceneListener;
import Controllers.GameScenes.MenuGameScene;
import Models.GameSetting;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by Minh on 7/27/2016.
 */
public class GameWindow extends Frame implements Runnable, GameSceneListener{

    BufferedImage bufferedImage;
    Graphics bufferedImageGraphics;
    Thread thread;
    GameSetting gameSetting;
    GameScene currentGameScene;

    public GameWindow(){
        configUI();
        changeGameScene(new MenuGameScene());
        this.bufferedImage = new BufferedImage(gameSetting.getScreenWidth(),gameSetting.getScreenHeight(),BufferedImage.TYPE_3BYTE_BGR);
        this.bufferedImageGraphics = bufferedImage.getGraphics();
        thread = new Thread(this);
        thread.start();
    }
    private void configUI(){
        gameSetting = GameSetting.getInstance();
        this.setVisible(true);
        this.setSize(gameSetting.getScreenWidth(), gameSetting.getScreenHeight());
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
    }
    @Override
    public void update(Graphics g){
        this.currentGameScene.draw(bufferedImageGraphics);
        //bufferedImageGraphics.drawString("HP: " + PlaneController.planeController.gameObjectWithHP().getHp(), 300, 400);
        g.drawImage(bufferedImage,0,0,null);
    }
    public void run(){
        while(true) {
            try {
                this.currentGameScene.run();
                Thread.sleep(gameSetting.getThreadDelay());
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void changeGameScene(GameScene gameScene) {
        currentGameScene = gameScene;
        currentGameScene.setGameSceneListener(this);
        this.addKeyListener(gameScene.getKeyListener());
    }
}