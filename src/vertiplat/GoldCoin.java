package vertiplat;

import city.cs.engine.*;

public class GoldCoin extends DynamicBody {
    private static final Shape      coinShape = new CircleShape(0.58f);
    private static final BodyImage  coinImg = new BodyImage("data/yos_coin.png");
    private int                     baseValue = 1;
    private Spider                  spider;
    private HPpot                   hpPot;

    public GoldCoin(BaseLevel world) {
        super(world, coinShape);
        setImage(coinImg);
        this.addCollisionListener(new CollisionHandler(world.getPlayer(), spider, this, hpPot));
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }
}
