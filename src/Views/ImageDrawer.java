package Views;

import Models.GameObject;
import Utils.Utils;

import java.awt.*;

/**
 * Created by Minh on 8/2/2016.
 */
public class ImageDrawer implements GameDrawer {

    private Image img;

    public ImageDrawer(Image img) {
        this.img = img;
    }
    public ImageDrawer(String url){
        this.img = Utils.loadImage(url);
    }

    @Override
    public void draw(Graphics g, GameObject gameObject) {
        g.drawImage(img, gameObject.getX(), gameObject.getY(),
                gameObject.getWidth(), gameObject.getHeight(), null );
    }
}
