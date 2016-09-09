package Controllers.Plane;

import Controllers.Bomb.BombController;
import Controllers.Bomb.BombController2;
import Controllers.Bomb.BombManager;
import Controllers.Bomb.BombType;
import Controllers.ControllerManager;
import Models.GameSetting;

import java.util.Random;

/**
 * Created by NHAN on 8/21/2016.
 */
public class FireBallManager extends ControllerManager {
    private FireBallManager(){
        super();
    }
    private static FireBallManager inst;
    public static FireBallManager getInst(){
        if(inst == null){
            inst = new FireBallManager();
        }
        return inst;
    }

    @Override
    public void run() {
        super.run();

    }
}
