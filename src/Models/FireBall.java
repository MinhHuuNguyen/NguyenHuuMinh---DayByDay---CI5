package Models;

/**
 * Created by Minh on 8/20/2016.
 */
public class FireBall extends GameObject{
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    public FireBall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public FireBall(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
