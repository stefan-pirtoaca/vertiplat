package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Pan the view to follow a particular body.
 */
public class Tracker implements StepListener {
    
    private final NormalView      view;
    private final Body            body;

    public Tracker(NormalView view, Body body) {
        this.view = view;
        this.body = body;
    }

    public void preStep(StepEvent e) {
    }

    @Override
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(body.getPosition()));
    }
}
