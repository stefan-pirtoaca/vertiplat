package vertiplat;

import city.cs.engine.*;

public class EnemyWalker implements StepListener{
    
    private static final int    seconds = 60;
    private static Walker       walkRight;
    private static Walker       walkLeft;
    private static World        world;
    private static boolean      goingRight = true;
    private static long         time = 0;                                       //no see
    private final int           timeToWalk;                                     //for how much time the characer goes left/right
    private final Spider        body;
    
    public EnemyWalker(Spider body, float speed, int timeToWalk) {
        this.body = body;
        this.timeToWalk = timeToWalk;
        walkRight = new Walker(body, speed);
        walkLeft = new Walker(body, -speed);
        world = body.getWorld();
        world.addStepListener(walkRight);
    }
    
    @Override
    public void preStep(StepEvent e) {
        if(time % (timeToWalk * seconds) == 0 && goingRight) {
            world.removeStepListener(walkRight);
            world.addStepListener(walkLeft);
            goingRight = false;
            body.switchImage("left");
        } else if(time % (timeToWalk * seconds) == 0){
            world.removeStepListener(walkLeft);
            world.addStepListener(walkRight);
            goingRight = true;
            body.switchImage("right");
        }
        time++;
    }
    
    @Override
    public void postStep(StepEvent e) {
        
    }
}
