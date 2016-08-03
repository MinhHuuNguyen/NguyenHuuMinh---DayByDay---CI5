package Models;

/**
 * Created by Minh on 7/29/2016.
 */
public class Plane extends GameObject{
    public static final int WIDTH = 100;
    public static final int HEIGHT = 70;
    public Plane(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}