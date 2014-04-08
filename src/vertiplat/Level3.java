package vertiplat;

import city.cs.engine.*;
import java.net.MalformedURLException;
import org.jbox2d.common.Vec2;

public class Level3 extends BaseLevel{
    
    private static final float      wallX = 40;
    private static final int        goldCoinNumber = 21;
    private static final BodyImage  bricks = new BodyImage("data/surface.png", 5);
    private static float            groundPos;
    private static float            blockSize;
    private GoldCoin                coin;
    private Spider                  spider1, spider2, spider3, spider4, spider5,
                                    spider6, spider7;
    
    @Override
    public void build(GameClient game) throws MalformedURLException {
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
            Body platform6 = new StaticBody(this, shape);
            Body platform7 = new StaticBody(this, shape);
            platform1.setPosition(new Vec2(-wallX + 6*blockSize, -3.15f));
            platform2.setPosition(new Vec2(0, 8f));
            platform3.setPosition(new Vec2(wallX - 6*blockSize, -3.15f));
            platform4.setPosition(new Vec2(-wallX + 10*blockSize, 2.5f));
            platform5.setPosition(new Vec2(wallX - 10*blockSize, 2.5f));
            platform6.setPosition(new Vec2(-wallX + 4*blockSize, 8));
            platform7.setPosition(new Vec2(wallX - 4*blockSize, 8));
            platform1.setImage(bricks); 
            platform2.setImage(bricks);
            platform3.setImage(bricks);
            platform4.setImage(bricks);
            platform5.setImage(bricks);
            platform6.setImage(bricks);
            platform7.setImage(bricks);
         }
        
        for(int i = -10; i <= -5; i++) {                                       //Gold coins
            coin = new GoldCoin(this);
            coin.setPosition(new Vec2(i*3, 3.5f));
         }
         for(int i = 5; i <= 10; i++) {
            coin = new GoldCoin(this);
            coin.setPosition(new Vec2(i*3, 3.5f));
         }
         for(int i = -4; i <= 4; i++) {
            coin = new GoldCoin(this);
            coin.setPosition(new Vec2(i*3, 8.5f));
         }
         
         {                                                                      //Enemies    
            spider1 = new Spider(this, 3);
            spider2 = new Spider(this, 3);
            spider3 = new Spider(this, 3);
            spider4 = new Spider(this, 6);
            spider5 = new Spider(this, 6);
            spider7 = new Spider(this, 6);
            spider6 = new Spider(this, 6);
            spider1.setPosition(new Vec2(wallX - 3 * blockSize, -2.5f));
            spider2.setPosition(new Vec2(-wallX + 3 * blockSize, -2.5f));
            spider3.setPosition(new Vec2(0, 8.3f));
            spider4.setPosition(new Vec2(wallX - 3 * blockSize, -7f));
            spider5.setPosition(new Vec2(-wallX + 3 * blockSize, -7f));
            spider7.setPosition(new Vec2(wallX - 3 * blockSize, 3f));
            spider6.setPosition(new Vec2(-wallX + 3 * blockSize, 3f));
         }
    }
    
    @Override
    public Vec2 spawnPoint() {
        return new Vec2(0, -6f);
    }
    
    @Override
    public Vec2 exitPoint() {
        return new Vec2(0, 15);
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
