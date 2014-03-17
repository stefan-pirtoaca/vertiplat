package vertiplat;

import city.cs.engine.*;

public class CollisionHandler implements CollisionListener {
    
    private final Luke luke;
    private final GoldCoin coin;
    private final Spider spider;
    private final HPpot hpPot;
    
    public CollisionHandler(Luke luke, Spider spider, GoldCoin coin, HPpot hpPot) {
        this.luke = luke;
        this.spider = spider;
        this.coin = coin;
        this.hpPot = hpPot;
    }
      
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() == luke) {
            if(e.getReceivingBody() instanceof GoldCoin) {
                luke.increaseGold(coin.getBaseValue());
                e.getReceivingBody().destroy();
                System.out.println("Collision happened at: " + "" +
                        e.getPosition() + " and current gold is " + "" +
                        luke.getGold());                                        //for debugging
            } else if(e.getReceivingBody() instanceof Spider &&
                      e.getOtherFixture() != luke.getFixtureList().get(0)) {
                spider.damage(luke);
              //luke.setLinearVelocity(new Vec2(2, luke.getLinearVelocity().y));
                System.out.println("Luke's HP is " + luke.getHP());             //for debugging
              //System.out.println(e.getOtherFixture());
            } else if(e.getOtherFixture() == luke.getFixtureList().get(0) &&
                      e.getReceivingBody() instanceof Spider) {
                luke.damageSpider(spider);
                System.out.println("Spider HP is "  +spider.getHP());
            } else if(e.getReceivingBody() instanceof HPpot) {
                hpPot.heal(luke);
                System.out.println("Luke's HP is " + luke.getHP());             //for debugging
                hpPot.destroy();
            }
        }
    }
}
