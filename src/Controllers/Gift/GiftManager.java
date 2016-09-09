package Controllers.Gift;

import Controllers.ControllerManager;
import Controllers.Lock.LockController;
import Controllers.Lock.LockManager;
import Models.GameSetting;

import java.util.Random;

/**
 * Created by Minh on 8/20/2016.
 */
public class GiftManager extends ControllerManager {
    private int count = 0;
    private static final int GIFT_PERIOD = 50;
    private GiftManager(){
        super();
    }
    public static GiftManager inst;
    public static GiftManager getInst(){
        if(inst == null){
            inst = new GiftManager();
        }
        return inst;
    }
    @Override
    public void run() {
        count++;
        super.run();
        Random rand = new Random();
        if (count == GIFT_PERIOD){
            count = 0;
            int  x = rand.nextInt(GameSetting.getInstance().getScreenWidth()) + 1;
            GiftController giftController = GiftController.create(x, 0);
            this.add(giftController);
        }
    }
}
