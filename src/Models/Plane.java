package Models;

/**
 * Created by Minh on 7/29/2016.
 */
public class Plane extends GameObjectWithHP {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 70;
    private static final int HP_DEFAULT = 500;

    public Plane(int x, int y, int width, int height, int hp) {
        super(x, y, width, height, hp);
    }
    public Plane(int x, int y) {
        super(x, y, WIDTH, HEIGHT, HP_DEFAULT);

    }
}