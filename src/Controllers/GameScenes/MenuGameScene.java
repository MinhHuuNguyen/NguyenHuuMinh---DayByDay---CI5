package Controllers.GameScenes;

import Utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Minh on 8/17/2016.
 */
public class MenuGameScene implements GameScene, KeyListener {
    private GameSceneListener gameSceneListener;
    private Image background;

    public MenuGameScene() {
        background = Utils.loadImage("resources/menu_background.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    @Override
    public KeyListener getKeyListener() {
        return this;
    }

    @Override
    public void setGameSceneListener(GameSceneListener gameSceneListener) {
        this.gameSceneListener = gameSceneListener;
    }

    @Override
    public void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gameSceneListener.changeGameScene(new PlayGameScene());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
