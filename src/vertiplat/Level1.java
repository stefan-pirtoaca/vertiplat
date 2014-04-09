package vertiplat;

import city.cs.engine.*;
import java.net.MalformedURLException;
import org.jbox2d.common.Vec2;

public class Level1 extends BaseLevel {

    private static float blockSize;
    private static final float wallX = 20;
    private static final BodyImage bricks = new BodyImage("data/surface.png", 5);
    private static final int goldCoinNumber = 13;
    private GoldCoin coin;
    private Spider spider1, spider2, spider3;

    @Override
    public void build(GameClient game) throws MalformedURLException {

        super.build(game);
        blockSize = super.getBlockSize();

        {                                                                       //Platforms
            Shape shape = new BoxShape(blockSize * 3, blockSize / 4);
            bricks.setClipped(true);
            Body platform1 = new StaticBody(this, shape);
            Body platform2 = new StaticBody(this, shape);
            Body platform3 = new StaticBody(this, shape);
            platform1.setPosition(new Vec2(-wallX + 3 * blockSize, -3.15f));
            platform2.setPosition(new Vec2(0, 2.5f));
            platform3.setPosition(new Vec2(wallX - 3 * blockSize, -3.15f));
            platform1.setImage(bricks);
            platform2.setImage(bricks);
            platform3.setImage(bricks);
        }

        for (int i = -6; i <= 6; i++) {                                         //Some gold coins
            coin = new GoldCoin(this);
            coin.setPosition(new Vec2(i * 2, 3.5f));
        }

        spider1 = new Spider(this, 2);                                          //Enemies
        spider2 = new Spider(this, 2);
        spider3 = new Spider(this, 2);
        spider1.setPosition(new Vec2(wallX - 3 * blockSize, -2.5f));
        spider2.setPosition(new Vec2(-wallX + 3 * blockSize, -2.5f));
        spider3.setPosition(new Vec2(0, 3.6f));

        HPpot hpPot = new HPpot(this);
        hpPot.setPosition(new Vec2(-7, 3.5f));
    }

    @Override
    public Vec2 spawnPoint() {
        return new Vec2(0, -6f);
    }

    @Override
    public Vec2 exitPoint() {
        return new Vec2(0, 10);
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
