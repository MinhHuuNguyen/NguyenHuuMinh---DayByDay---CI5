package Controllers;

import Models.Enemy;
import Models.GameConfig;
import Views.ImageDrawer;

import java.util.Random;

/**
 * Created by Minh on 8/2/2016.
 */
public class EnemyManager extends ControllerManager {

    private int count;
    private EnemyManager() {
        super();
        int enX = 10;
        int enY = 10;
//        for(int i = 0; i < 5; i++){
//            EnemyController enemyController = new EnemyController(new Enemy(enX, enY),new ImageDrawer("resources/plane1.png"));
//            enX += 30;
//            this.add(enemyController);
//        }

    }
    private static EnemyManager inst;
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
        count = rand.nextInt(150) + 1;
        if (count == 1){
            int  x = rand.nextInt(GameConfig.getInst().getScreenWidth()) + 1;
            Enemy enemyPlane = new Enemy (x, 0);
            ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_plane_white_1.png");
            EnemyController enemyController = new EnemyController(enemyPlane, imageDrawer);
            this.singleControllerVector.add(enemyController);
        }
        if (count == 2){
            int  y = rand.nextInt(GameConfig.getInst().getScreenHeight()) / 2 + 1;
            Enemy enemyPlane2 = new Enemy(0 , y);
            ImageDrawer imageDrawer2 = new ImageDrawer("resources/enemy_plane_yellow_1.png");
            EnemyController2 enemyController2 = new EnemyController2(enemyPlane2, imageDrawer2);
            this.singleControllerVector.add(enemyController2);
        }
    }
}
