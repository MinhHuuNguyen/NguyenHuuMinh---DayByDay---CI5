package Models;

/**
 * Created by Minh on 7/29/2016.
 */
public class Enemy extends GameObjectWithHP{
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;
    private static final int DEFAULT_HP = 2;
    public Enemy(int x, int y, int width, int height, int hp) {
        super(x, y, width, height, hp);
    }
    public Enemy(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_HP);
    }
}
