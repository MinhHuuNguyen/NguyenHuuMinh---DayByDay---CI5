package Controllers.GameScenes;

import Controllers.Bomb.BombManager;
import Controllers.CollisionPool;
import Controllers.Enemy.EnemyBulletManager;
import Controllers.Enemy.EnemyManager;
import Controllers.Gift.GiftManager;
import Controllers.Lock.LockManager;
import Controllers.Plane.FireBallManager;
import Controllers.Plane.PlaneController;
import Utils.Utils;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Minh on 8/17/2016.
 */
public class PlayGameScene implements GameScene{
    private Image background;
    private GameSceneListener gameSceneListener;

    public PlayGameScene() {
        background = Utils.loadImage("resources/background.png");
//        if (PlaneController)
    }

    public void setGameSceneListener(GameSceneListener gameSceneListener) {
//        this.gameSceneListener = gameSceneListener;
        PlaneController.instance.setGameSceneListener(gameSceneListener);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        PlaneController.instance.draw(g);
        EnemyManager.getInst().draw(g);
        EnemyBulletManager.enemyBulletManager.draw(g);
        BombManager.getInst().draw(g);
        LockManager.getInst().draw(g);
        GiftManager.getInst().draw(g);
        FireBallManager.getInst().draw(g);
    }

    @Override
    public KeyListener getKeyListener() {
        return PlaneController.instance;
    }

    @Override
    public void run() {
        PlaneController.instance.run();
        EnemyManager.getInst().run();
        EnemyBulletManager.enemyBulletManager.run();
        CollisionPool.getInst().run();
        BombManager.getInst().run();
        LockManager.getInst().run();
        GiftManager.getInst().run();
        FireBallManager.getInst().run();
    }
}
