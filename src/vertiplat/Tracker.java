package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Pan the view to follow a particular body.
 */
public class Tracker implements StepListener {
    /** The view */
    private WorldView view;

    /** The body */
    private Body body;

    public Tracker(WorldView view, Body body) {
        this.view = view;
        this.body = body;
    }

    /**
     */
    public void preStep(StepEvent e) {
    }

    /**
     */
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(body.getPosition()));
    }
    
}
