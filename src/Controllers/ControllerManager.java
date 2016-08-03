package Controllers;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Minh on 8/2/2016.
 */
public class ControllerManager implements BaseController{
    protected Vector<SingleController> singleControllerVector;

    public ControllerManager() {
        singleControllerVector = new Vector<SingleController>();
    }

    public void add(SingleController singleController){
        this.singleControllerVector.add(singleController);
    }

    @Override
    public void draw(Graphics g) {
        for (BaseController controller : this.singleControllerVector){
            controller.draw(g);
        }
    }

    @Override
    public void run() {
        Iterator<SingleController> singleControllerIterator = this.singleControllerVector.iterator();
        while (singleControllerIterator.hasNext()){
            SingleController singleController = singleControllerIterator.next();
            if(!singleController.getGameObject().isAlive()){
                singleControllerIterator.remove();
            } else{
                singleController.run();
            }
        }
    }
}
