package Controllers.Lock;

import Controllers.ControllerManager;
import Models.GameConfig;

import java.util.Random;

/**
 * Created by Minh on 8/12/2016.
 */
public class LockManager extends ControllerManager{
    private int count;
    private LockManager(){
        super();
    }
    public static LockManager inst;
    public static LockManager getInst(){
        if(inst == null){
            inst = new LockManager();
        }
        return inst;
    }
    @Override
    public void run() {
        super.run();
        Random rand = new Random();
        count = rand.nextInt(200) + 1;
        int  x = rand.nextInt(GameConfig.getInst().getScreenWidth()) + 1;
        if (count == 1){
            LockController lockController = LockController.create(x, 0);
            this.add(lockController);
        }
    }
}
