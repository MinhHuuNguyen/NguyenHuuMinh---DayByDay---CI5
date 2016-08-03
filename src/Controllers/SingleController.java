package Controllers;

import Models.GameObject;
import Models.GameVector;
import Views.GameDrawer;

import java.awt.*;

/**
 * Created by Minh on 8/2/2016.
 */
public class SingleController implements BaseController {

    protected GameObject gameObject;
    private GameDrawer gameDrawer;
    protected GameVector gameVector;

    public SingleController(GameObject gameObject, GameDrawer gameDrawer) {
        this.gameObject = gameObject;
        this.gameDrawer = gameDrawer;
        this.gameVector = new GameVector();
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    @Override
    public void draw(Graphics g) {
        gameDrawer.draw(g, gameObject);
    }

    @Override
    public void run() {
        gameObject.move(this.gameVector);
    }
}
