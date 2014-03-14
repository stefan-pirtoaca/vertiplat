package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level2 extends BaseLevel{
    
    private static final float GROUND_LENGTH = 100;
    private static final float GROUND_POS = -10;
    private static final float WALL_HEIGHT = 50; 
    private static final float WALL_X = 50; //to match ground length, do GROUND_LENGTH/2
    private static final float BLOCK_SIZE = 2.5f; //half, actual block size is 5
    private static final BodyImage bricks = new BodyImage("data/surface.png", 5);
    private GoldCoin coin;
    
    @Override
    public void build(GameClient game) {
        super.build(game);
        
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
    
    @Override
    public Vec2 spawnPoint() {
        return new Vec2(0, 0);
    }
    
    @Override
    public Vec2 exitPoint() {
        return new Vec2(0, 20);
    }
    
    @Override
    public boolean isCompleted() {
        return true;
    }
}
