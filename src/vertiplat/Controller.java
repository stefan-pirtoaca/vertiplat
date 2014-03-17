package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key handler to control a body.
 * Implemented controls repeated here, for easier reference:
 * Left arrow key = walk left
 * Right arrow key = walk right
 * Space = jump
 * X = attack
 * Q = quit
 */

public class Controller extends KeyAdapter {
    
    private static final float  JUMPING_SPEED = 11;
    private static final float  WALKING_SPEED = 7;
    private Luke                body;
    private World               world;
    private final Walker        walkLeft;
    private final Walker        walkRight;
    private boolean             walking = false;                                //fixed windows bug that kept adding listeners
    private boolean             attacking = false;                              //same fix for attacking
    private int                 lastDirectionOfWalking = KeyEvent.VK_RIGHT;     //basically, it retains the KeyCode of the last
                                                                                //KeyEvent, lets me know which attack image to use  
    private final BodyImage     walkRightImg = new BodyImage("data/LukeRight.png", 4f);
    private final BodyImage     walkLeftImg = new BodyImage("data/LukeLeft.png", 4f);
    
    
    public Controller(Luke body) {
        this.body = body;
        this.world = body.getWorld();
        this.walkLeft = new Walker(body, - WALKING_SPEED);
        this.walkRight = new Walker(body, WALKING_SPEED);
    }
    
    /** Handle key press events for walking(arrow keys),
     * jumping(space bar) and attacking(x). */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) {                                            // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_SPACE) { // SPACE = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.setLinearVelocity(new Vec2(v.x, JUMPING_SPEED));
            }
        } else if (code == KeyEvent.VK_LEFT  && !walking) {
            // LEFT ARROW = walk left
            world.addStepListener(walkLeft);
            body.setImage(walkLeftImg);
            walking = true;
            lastDirectionOfWalking = code;
        } else if (code == KeyEvent.VK_RIGHT && !walking) {
            //RIGHT ARROW = walk right
            world.addStepListener(walkRight);
            body.setImage(walkRightImg);
            //walking = true;
            lastDirectionOfWalking = code;
        } else if(code == KeyEvent.VK_X && !attacking) { //X = attack
            attacking = true;
                if (lastDirectionOfWalking == KeyEvent.VK_RIGHT) {
                    body.attackRight(body);
                } else if(lastDirectionOfWalking == KeyEvent.VK_LEFT) {
                    body.attackLeft(body);
                }
        } 
    }
    
    /** Handle key release events (stop walking, stop attacking). */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            world.removeStepListener(walkLeft);
            walking = false;
        } else if (code == KeyEvent.VK_RIGHT) {
            world.removeStepListener(walkRight);
            walking = false;
        } else if (code == KeyEvent.VK_X) {
            body.endAttack();
            attacking = false;
            if (lastDirectionOfWalking == KeyEvent.VK_RIGHT) {
                body.setImage(walkRightImg);
            } else if (lastDirectionOfWalking == KeyEvent.VK_LEFT) {
                body.setImage(walkLeftImg);
            }
        }
    }
    
        public void setBody(Luke luke) {
        body = luke;
        this.world = luke.getWorld();
        walkLeft.setBody(body);
        walkRight.setBody(body);
    }
}
