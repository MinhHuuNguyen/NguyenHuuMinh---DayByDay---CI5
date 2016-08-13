package Models;

/**
 * Created by Minh on 8/3/2016.
 */
public class EnemyBullet extends GameObject {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public EnemyBullet(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
