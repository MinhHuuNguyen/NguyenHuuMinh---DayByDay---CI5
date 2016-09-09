package Controllers.Bomb;

import Controllers.ControllerManager;
import Models.GameSetting;

import java.util.Random;

/**
 * Created by Minh on 8/9/2016.
 */
public class BombManager extends ControllerManager {
    private int count;
    private BombManager(){
        super();
    }
    public static BombManager inst;
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
        count = rand.nextInt(400) + 1;
        int  x = rand.nextInt(GameSetting.getInstance().getScreenWidth()) + 1;
        if (count == 1){
            BombController bombController = BombController.create(x, 0,  BombType.SMALL);
            this.add(bombController);
        }
        if (count == 2){
            BombController2 bombController2 = BombController2.create(x, 0,  BombType.BIG);
            this.add(bombController2);
        }
    }
}

