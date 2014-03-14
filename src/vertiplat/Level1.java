package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level1 extends BaseLevel {

    private static final int goldCoinNumber = 13;
    private static final float GROUND_LENGTH = 100;
    private static final float GROUND_POS = -10;
    private static final float WALL_HEIGHT = 50;
    private static final float WALL_X = 20; //to match ground length, do GROUND_LENGTH/2
    private static final float BLOCK_SIZE = 2.5f; //half, actual block size is 5
    private static final BodyImage bricks = new BodyImage("data/surface.png", 5);
    private GoldCoin coin;
    private Spider spider;

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
         
         {
         //Platforms
          Shape shape = new BoxShape(BLOCK_SIZE*2, BLOCK_SIZE/4);
          bricks.setClipped(true);
          Body platform1 = new StaticBody(this, shape);
          Body platform2 = new StaticBody(this, shape);
          Body platform3 = new StaticBody(this, shape);
          platform1.setPosition(new Vec2(-WALL_X + 3*BLOCK_SIZE, -3.15f));
          platform2.setPosition(new Vec2(0, 2.5f));
          platform3.setPosition(new Vec2(WALL_X - 3*BLOCK_SIZE, -3.15f));
          platform1.setImage(bricks);
          platform2.setImage(bricks);
          platform3.setImage(bricks);
         }
          
          //Some gold coins
          for(int i = -6; i <= 6; i++) {
            coin = new GoldCoin(this);
            coin.setPosition(new Vec2(i*2, 3));
        }
          
          //Enemies
          spider = new Spider(this);
          spider.setPosition(new Vec2(WALL_X - 3*BLOCK_SIZE, 2.5f));
          
          System.out.println(this.getPlayer().getFixtureList());
    }
    
    public Spider getSpider() {
        return spider;
    }
    
    @Override
    public Vec2 spawnPoint() {
        return new Vec2(0, -7f);
    }
    
    @Override
    public Vec2 exitPoint() {
        return new Vec2(0, 10);
    }
    
    @Override
    public boolean isCompleted() {
       if(super.getPlayer().getGold() == goldCoinNumber)
        return true;
       else return false;
    }
}