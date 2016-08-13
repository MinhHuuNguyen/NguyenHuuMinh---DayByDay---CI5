package Models;

/**
 * Created by Minh on 8/12/2016.
 */
public class Lock extends GameObject {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    public Lock(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public Lock(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
