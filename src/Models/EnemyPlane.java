package Models;

/**
 * Created by Minh on 7/29/2016.
 */
public class EnemyPlane {
    public int x;
    public int y;
    public int dx;
    public int dy;
    public void moveTo(int x, int y){
        this.x = x;
        this.y = y;
    }
}
