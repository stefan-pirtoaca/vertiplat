package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level2 extends BaseLevel{
    
    private static float            groundPos;
    private static final float      wallX = 40;
    private static float            blockSize;
    private static final BodyImage  bricks = new BodyImage("data/surface.png", 5);
    private static final int        goldCoinNumber = 12;
    private GoldCoin                coin;
    private Spider                  spider1, spider2, spider3, spider4, spider5;
    
    @Override
    public void build(GameClient game) {
        super.build(game);
        groundPos = super.getGroundPos();
        blockSize = super.getBlockSize();

         {																		//Platforms
            Shape shape = new BoxShape(blockSize*4, blockSize/4);
            Shape smallShape = new BoxShape(blockSize*2, blockSize/4);
            bricks.setClipped(true);
            Body platform1 = new StaticBody(this, shape);
            Body platform2 = new StaticBody(this, shape);
            Body platform3 = new StaticBody(this, shape);
            Body platform4 = new StaticBody(this, smallShape);
            Body platform5 = new StaticBody(this, smallShape);
            platform1.setPosition(new Vec2(-wallX + 6*blockSize, -3.15f));
            platform2.setPosition(new Vec2(0, 8f));
            platform3.setPosition(new Vec2(wallX - 6*blockSize, -3.15f));
            platform4.setPosition(new Vec2(-15f, 2.5f));
            platform5.setPosition(new Vec2(15f, 2.5f));
            platform1.setImage(bricks); 
            platform2.setImage(bricks);
            platform3.setImage(bricks);
            platform4.setImage(bricks);
            platform5.setImage(bricks);
         }
         
         for(int i = -10; i <= -5; i++) {                                       //Gold coins
            coin = new GoldCoin(this);
            coin.setPosition(new Vec2(i*3, 3.5f));
         }
         for(int i = 5; i <= 10; i++) {
            coin = new GoldCoin(this);
            coin.setPosition(new Vec2(i*3, 3.5f));
         }
         
         {
            HPpot hpPot1 = new HPpot(this);
            HPpot hpPot2 = new HPpot(this);
            hpPot1.setPosition(new Vec2(wallX - blockSize * 3, -groundPos*2));
            hpPot2.setPosition(new Vec2(-wallX + blockSize * 3, 3.5f));
         }
         
         {
            spider1 = new Spider(this, 3);                                      //Enemies
            spider2 = new Spider(this, 3);
            spider3 = new Spider(this, 3);
            spider4 = new Spider(this, 6);
            spider5 = new Spider(this, 6);
            spider1.setPosition(new Vec2(wallX - 3 * blockSize, -2.5f));
            spider2.setPosition(new Vec2(-wallX + 3 * blockSize, -2.5f));
            spider3.setPosition(new Vec2(0, 8.3f));
            spider4.setPosition(new Vec2(wallX - 3 * blockSize, -7f));
            spider5.setPosition(new Vec2(-wallX + 3 * blockSize, -7f));
         }
    }
    
    @Override
    public Vec2 spawnPoint() {                                                  //player spawn point
        return new Vec2(0, -6f);
    }
    
    @Override
    public Vec2 exitPoint() {                                                   //exit spawn point
        return new Vec2(0, 12);
    }
    
    @Override
    public boolean isCompleted() {
        return super.getPlayer().getGold() == goldCoinNumber;
    }
    
    @Override
    public float wallX() {
        return wallX;
    }
    
    @Override
    public int getGoldCoinNumber() {
        return goldCoinNumber;
    }
}
