package vertiplat;

import city.cs.engine.*;

public class EnemyWalker implements StepListener{
    
    private static final int    seconds = 60;
    private static Walker       walkRight;
    private static Walker       walkLeft;
    private static World        world;
    private static boolean      goingRight = true;
    private static int          time = 0;
    private final float         speed;
    private final DynamicBody   body;
    
    public EnemyWalker(DynamicBody body, float speed) {
        this.body = body;
        this.speed = speed;
        walkRight = new Walker(body, speed);
        walkLeft = new Walker(body, -speed);
        world = body.getWorld();
        world.addStepListener(walkRight);
    }
    
    @Override
    public synchronized void preStep(StepEvent e) {
        if(time % (2 * seconds) == 0 && goingRight) {
            world.removeStepListener(walkRight);
            world.addStepListener(walkLeft);
            goingRight = false;
        } else if(time % (2 * seconds) == 0){
            world.removeStepListener(walkLeft);
            world.addStepListener(walkRight);
            goingRight = true;
        }
        time++;
    }
    
    @Override
    public void postStep(StepEvent e) {
        
    }
}
