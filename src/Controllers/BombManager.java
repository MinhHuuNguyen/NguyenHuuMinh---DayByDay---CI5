package Controllers;

import Models.Bomb;
import Models.Enemy;
import Models.GameConfig;
import Views.ImageDrawer;

import java.util.Random;

/**
 * Created by Minh on 8/9/2016.
 */
public class BombManager extends ControllerManager {
    private int count;
    private BombManager(){
        super();
    }
    private static BombManager inst;
    public static BombManager getInst(){
        if(inst == null){
            inst = new BombManager();
        }
        return inst;
    }

    @Override
    public void run() {
        super.run();
        Random rand = new Random();
        count = rand.nextInt(100) + 1;
        int  x = rand.nextInt(GameConfig.getInst().getScreenWidth()) + 1;
        Bomb bomb = new Bomb (x, 0);
        if (count == 1){
            ImageDrawer imageDrawer = new ImageDrawer("resources/bomb_1.png");
            BombController bombController = new BombController(bomb, imageDrawer);
            this.singleControllerVector.add(bombController);
        }
        if (count == 2){
            ImageDrawer imageDrawer2 = new ImageDrawer("resources/bomb_2.png");
            BombController2 bombController2 = new BombController2(bomb, imageDrawer2);
            this.singleControllerVector.add(bombController2);
        }
    }
}

