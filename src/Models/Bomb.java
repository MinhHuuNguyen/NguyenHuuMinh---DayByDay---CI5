package Models;

/**
 * Created by Minh on 8/9/2016.
 */
public class Bomb extends GameObject{
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    public Bomb(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public Bomb(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
