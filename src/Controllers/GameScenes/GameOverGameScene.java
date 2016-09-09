package Controllers.GameScenes;

import Utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Minh on 8/17/2016.
 */
public class GameOverGameScene implements GameScene, KeyListener {

    private Image background;
    private GameSceneListener gameSceneListener;

    public GameOverGameScene() {
        background = Utils.loadImage("resources/game_over.png");
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gameSceneListener.changeGameScene(new MenuGameScene());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

    }
}
