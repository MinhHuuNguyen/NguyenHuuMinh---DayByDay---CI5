package Controllers.Lock;

import Controllers.ControllerManager;
import Models.GameSetting;

import java.util.Random;

/**
 * Created by Minh on 8/12/2016.
 */
public class LockManager extends ControllerManager{
    private int count = 0;
    private static final int LOCK_PERIOD = 200;
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
        count++;
        super.run();
        Random rand = new Random();
        if (count == LOCK_PERIOD){
            count = 0;
            int  x = rand.nextInt(GameSetting.getInstance().getScreenWidth()) + 1;
            LockController lockController = LockController.create(x, 0);
            this.add(lockController);
        }
    }
}
