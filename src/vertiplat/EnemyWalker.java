package vertiplat;

import city.cs.engine.*;

/**
 *
 * @author stefan
 */
public class EnemyWalker implements StepListener {

    private static final int seconds = 60;
    private static Walker walkRight;
    private static Walker walkLeft;
    private static World world;
    private static boolean goingRight = true;
    private static long time = 0;                                               //no see
    private final int timeToWalk;                                               //for how much time the characer goes left/right
    private final Spider body;

    /*A StepListener that makes enemies (spiders, for now) walk around.
     *What it's supposed to do:
     * @param body - enemy to move
     * @param speed - the speed at which the enemy will walk
     * @param timeToWalk - time in seconds at which the enemy changes direction
     *Known bug: if you attach this to more than one spider, the first spider
     * will walk right and switch images, but doesn't turn left, the second
     * spider will turn every 1 second and won't switch image. If you add any
     * more spiders, they will just go right without switching image.
     *I think it's because they share the same Walker class..? I don't have time
     * to fix it at the moment, will try after Milestone 2.
     */

    /**
     *
     * @param body
     * @param speed
     * @param timeToWalk
     */
    
    
    public EnemyWalker(Spider body, float speed, int timeToWalk) {
        this.body = body;
        this.timeToWalk = timeToWalk;
        walkRight = new Walker(body, speed);
        walkLeft = new Walker(body, -speed);
        world = body.getWorld();
        world.addStepListener(walkRight);
    }

    /**
     *
     * @param e
     */
    @Override
    public void preStep(StepEvent e) {
        if (time % (timeToWalk * seconds) == 0 && goingRight) {
            world.removeStepListener(walkRight);
            world.addStepListener(walkLeft);
            goingRight = false;
            body.switchImage("left");
        } else if (time % (timeToWalk * seconds) == 0) {
            world.removeStepListener(walkLeft);
            world.addStepListener(walkRight);
            goingRight = true;
            body.switchImage("right");
        }
        time++;
    }

    /**
     *
     * @param e
     */
    @Override
    public void postStep(StepEvent e) {

    }
}
