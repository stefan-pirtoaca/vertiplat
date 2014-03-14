package vertiplat;

import city.cs.engine.*;

public class CollisionHandler implements CollisionListener {
    
    private Luke luke;
    private final GoldCoin coin;
    private final Spider spider;
    
    public CollisionHandler(Luke luke, Spider spider, GoldCoin coin) {
        this.luke = luke;
        this.spider = spider;
        this.coin = coin;
    }
      
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() == luke) {
            if(e.getReceivingBody() instanceof GoldCoin) {
                luke.increaseGold(coin.getBaseValue());
                e.getReceivingBody().destroy();
                System.out.println("Collision happened at: " + "" + e.getPosition() + 
                     " and current gold is " + "" + luke.getGold());
            } else if(e.getReceivingBody() instanceof Spider
                    && e.getOtherFixture() != luke.getFixtureList().get(0)) {
                spider.damage(luke);
                //luke.setLinearVelocity(new Vec2(2, luke.getLinearVelocity().y));
                System.out.println("Luke's HP is " + luke.getHP());
                //System.out.println(e.getOtherFixture());
            } else if(e.getOtherFixture() == luke.getFixtureList().get(0)) {
                luke.damageSpider(spider);
                System.out.println("Spider HP is "  +spider.getHP());
            }
        }
    }
}
