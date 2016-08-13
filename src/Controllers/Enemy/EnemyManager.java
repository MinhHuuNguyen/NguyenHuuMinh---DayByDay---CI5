package Controllers.Enemy;

import Controllers.ControllerManager;
import Models.GameConfig;

import java.util.Random;

/**
 * Created by Minh on 8/2/2016.
 */
public class EnemyManager extends ControllerManager {

    private int count;
    private EnemyManager() {
        super();
    }
    public static EnemyManager inst;
    public static EnemyManager getInst(){
        if(inst == null){
            inst = new EnemyManager();
        }
        return inst;
    }
    @Override
    public void run() {
        super.run();
        Random rand = new Random();
        count = rand.nextInt(50) + 1;
        if (count == 1){
            int  x = rand.nextInt(GameConfig.getInst().getScreenWidth()) + 1;
            EnemyController enemyController = EnemyController.create(x, 0, EnemyType.WHITE, EnemyMovement.CROSS, EnemyShot.STRAIGHT);
            this.add(enemyController);
        }
        if (count == 2){
            int  x = rand.nextInt(GameConfig.getInst().getScreenWidth()) + 1;
            EnemyController enemyController = EnemyController.create(x, 0, EnemyType.YELLOW, EnemyMovement.STRAIGHT, EnemyShot.FOLLOWED);
            this.add(enemyController);
        }
    }
}
