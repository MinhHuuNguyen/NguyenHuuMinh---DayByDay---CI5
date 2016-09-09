package Models;

/**
 * Created by Minh on 8/20/2016.
 */
public class Gift  extends GameObject{
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    public Gift(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public Gift(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
