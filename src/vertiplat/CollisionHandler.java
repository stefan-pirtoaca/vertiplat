package vertiplat;

import city.cs.engine.*;

public class CollisionHandler implements CollisionListener {
    
    private final Luke      luke;
    private final GoldCoin  coin;
    private final Spider    spider;
    private final HPpot     hpPot;
    
    public CollisionHandler(Luke luke, Spider spider, GoldCoin coin, HPpot hpPot) {
        this.luke = luke;
        this.spider = spider;
        this.coin = coin;
        this.hpPot = hpPot;
    }
      
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() == luke) {
            if(e.getReceivingBody() instanceof GoldCoin) {                      //GoldCoin collision
                luke.increaseGold(coin.getBaseValue());
                e.getReceivingBody().destroy();
                System.out.println("Collision happened at: " + "" +
                        e.getPosition() + " and current gold is " + "" +
                        luke.getGold());                                        //for debugging
                
            } else if(e.getReceivingBody() instanceof Spider) {                 //Spider-player collision and damage exchange
                
                if(luke.getHP() > 0) {
                    spider.damage(luke);
                    System.out.println("Luke's HP is " + luke.getHP());         //for debugging
                } else {
                    luke.die();                                                 //player death and respawn
                    luke.setPosition(luke.getWorld().spawnPoint());
                    luke.setHP(luke.getMaxHP());
                }
                
                if(spider.getHP() > 0) {
                    luke.damageSpider(spider);
                    System.out.println("Spider HP is "  +spider.getHP());
                } else spider.destroy();
                
            } else if(e.getReceivingBody() instanceof HPpot) {                  //Hitpoints potion heals player
                hpPot.heal(luke);
                System.out.println("Luke's HP is " + luke.getHP());             //for debugging
                hpPot.destroy();
            }
        }
    }
}
