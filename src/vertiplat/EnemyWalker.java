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
    private static long time = 0;
    private final int timeToWalk;                                               //for how much time the characer goes left/right
    private final Spider body;

    /** StepListener that makes spiders walk around.
     * 
     * Known bug: if you attach this to more than one spider, the first spider
     * will walk right and switch images, but doesn't turn left, the second
     * spider will turn every 1 second and won't switch image. If you add any
     * more spiders, they will just go right without switching image.
     *
     * @param body - enemy to move
     * @param speed - the speed at which the enemy will walk
     * @param timeToWalk - time in seconds at which the enemy changes direction
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
