package Models;

/**
 * Created by Minh on 8/2/2016.
 */
public class GameConfig {
    public static final int DEFAULT_SCREEN_WIDTH = 600;
    public static final int DEFAULT_SCREEN_HEIGHT = 800;
    public static final int DEFAULT_THREAD_DELAY = 17;
    private int screenWidth;
    private int screenHeight;
    private int threadDelay;
    private GameConfig(int screenWidth, int screenHeight, int threadDelay){
         this.screenHeight = screenHeight;
         this.screenWidth = screenWidth;
         this.threadDelay = threadDelay;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int durationInMiliseconds(int count){
        return count * threadDelay;
    }
    public  int durationInSeconds (int count){
        return count *threadDelay / 1000;
    }
    public int getThreadDelay() {
        return threadDelay;
    }

    public boolean isInScreen(GameObject gameObject){
        return gameObject.getX() > 0 && gameObject.getX() < this.screenWidth && gameObject.getY() > 0 && gameObject.getY() < this.screenHeight;
    }
    private static GameConfig inst;
    public static GameConfig getInst(){
        if(inst == null){
            inst = new GameConfig(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT, DEFAULT_THREAD_DELAY);
        }
        return inst;
    }
}
