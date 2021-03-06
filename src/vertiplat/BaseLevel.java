package vertiplat;

import city.cs.engine.*;
import java.net.MalformedURLException;
import org.jbox2d.common.Vec2;

public abstract class BaseLevel extends World {
    
    /*
     * Basic level structure, such as ground, walls (can be different for each level).
     */
    
    private static final float      GROUND_LENGTH = 100;                        //constant to all levels
    private static final float      GROUND_POS = -10;                           //constant to all levels
    private static final float      WALL_HEIGHT = 50;                           //constant to all levels
    private static float            WALL_X;                                     //received as a parameter from each level
    private static final float      BLOCK_SIZE = 2.5f;                          //half, actual block size is 5
    private static final BodyImage  bricks = new BodyImage("data/surface.png", 5);
    private Luke                    luke;
    private LevelExit               exit;
    
    public void build(GameClient game) throws MalformedURLException {
        luke = new Luke(this);
        luke.setPosition(spawnPoint());
        exit = new LevelExit(this);
        exit.setPosition(exitPoint());
        exit.addCollisionListener(new ExitListener(game, exit));
        WALL_X = this.wallX();
        
                                                                                //Ground
        for(float i = -(GROUND_LENGTH/2); i <= GROUND_LENGTH/2; i += 2*BLOCK_SIZE) {
            Shape shape = new BoxShape(BLOCK_SIZE, BLOCK_SIZE);
            Body ground = new StaticBody(this, shape);
            ground.setPosition(new Vec2(i, GROUND_POS));
            bricks.setClipped(true);
            ground.setImage(bricks);
        }
         
                                                                                //Walls
         for(float i = GROUND_POS + 2*BLOCK_SIZE; i <= WALL_HEIGHT; i += 2*BLOCK_SIZE) {
             Shape shape = new BoxShape(BLOCK_SIZE, BLOCK_SIZE);
             Body leftWall = new StaticBody(this, shape);
             Body rightWall = new StaticBody(this, shape);
             leftWall.setPosition(new Vec2(-WALL_X, i));
             rightWall.setPosition(new Vec2(WALL_X, i));
             leftWall.setImage(bricks);
             rightWall.setImage(bricks);
         }
    }
    
    public abstract Vec2 spawnPoint();                                          //Player spawn point
       
    public abstract Vec2 exitPoint();                                           //exit spawn point
        
    public abstract boolean isCompleted();                                      //condition for level completion
    
    public abstract float wallX();                                              //used in level design, each level has it's "width"
    
    public abstract int getGoldCoinNumber();                                    //used in drawCoinsIndicator(), in NormalView
    
    /*
     * Useful after a dynamic bind to a concrete level, returns the player object
     * from that particular level.
     */
    public Luke getPlayer() {
        return luke;
    }
    
    public float getGroundPos() {                                               //mainly used in spawing HPPots
        return GROUND_POS;
    }
    
    public float getBlockSize() {                                               //used in building platforms
        return BLOCK_SIZE;
    }
}
