package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Key handler to control a Luke.
 * Implemented controls repeated here, for easier reference:
 * Left arrow key = walk left
 * Right arrow key = walk right
 * Space = jump
 * X = attack, spam attack is more effective than long pressing X
 * Q = quit
 */

public class Controller extends KeyAdapter {
    
    private static final float  JUMPING_SPEED = 11;
    private static final float  WALKING_SPEED = 9;
    private Luke                luke;
    private World               world;
    private final Walker        walkLeft;
    private final Walker        walkRight;
    private boolean             walking = false;                                //fixed windows bug that kept adding listeners
    private boolean             attacking = false;                              //same fix for attacking
    /*
     *lastDirectionOfWalking retains the KeyCode of the last KeyEvent, lets me 
     *know which attack image to use
     */
    private int                 lastDirectionOfWalking = KeyEvent.VK_RIGHT;
    private final BodyImage     walkRightImg = new BodyImage("data/LukeRight.png", 4f);
    private final BodyImage     walkLeftImg = new BodyImage("data/LukeLeft.png", 4f);
    
    
    public Controller(Luke Luke) {
        this.luke = Luke;
        this.world = Luke.getWorld();
        this.walkLeft = new Walker(Luke, -WALKING_SPEED);
        this.walkRight = new Walker(Luke, WALKING_SPEED);
    }
    
    /** Handle key press events for walking(arrow keys),
     * jumping(space bar) and attacking(x).
     * @param e */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) {                                            // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_SPACE) {                                 // SPACE = jump
            Vec2 v = luke.getLinearVelocity();
            // only jump if luke is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                luke.setLinearVelocity(new Vec2(v.x, JUMPING_SPEED));
            }
            luke.jumpSFX();
        } else if (code == KeyEvent.VK_LEFT  && !walking) {                     // LEFT ARROW = walk left
            world.addStepListener(walkLeft);
            luke.setImage(walkLeftImg);
            walking = true;
            lastDirectionOfWalking = code;
        } else if (code == KeyEvent.VK_RIGHT && !walking) {                     //RIGHT ARROW = walk right
            world.addStepListener(walkRight);
            luke.setImage(walkRightImg);
            //walking = true;
            lastDirectionOfWalking = code;
        } else if(code == KeyEvent.VK_X && !attacking) {                        //X = attack
            attacking = true;
                if (lastDirectionOfWalking == KeyEvent.VK_RIGHT) {
                    luke.attackRight(luke);
                } else if(lastDirectionOfWalking == KeyEvent.VK_LEFT) {
                    luke.attackLeft(luke);
                }
        } 
    }
    
    /** Handle key release events (stop walking, stop attacking).
     * @param e */
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
            luke.endAttack();
            attacking = false;
            if (lastDirectionOfWalking == KeyEvent.VK_RIGHT) {
                luke.setImage(walkRightImg);
            } else if (lastDirectionOfWalking == KeyEvent.VK_LEFT) {
                luke.setImage(walkLeftImg);
            }
        }
    }
    
    /*
     * Updates the body to control, between level switches.
     */
    public void setBody(Luke luke) {
        this.luke = luke;
        this.world = luke.getWorld();
        walkLeft.setBody(this.luke);
        walkRight.setBody(this.luke);
    }
}
