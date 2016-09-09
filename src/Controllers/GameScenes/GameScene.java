package Controllers.GameScenes;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Minh on 8/17/2016.
 */
public interface GameScene extends Runnable {
    void draw(Graphics g);
    KeyListener getKeyListener();
    void setGameSceneListener(GameSceneListener gameSceneListener);
}
