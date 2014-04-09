package vertiplat;

import city.cs.engine.*;

public class CollisionHandler implements CollisionListener {
    
    /*
     * Main collision handler, knows about collisions between the player and:
     *  Gold coins, Spiders, the Level exit and HP potions.
     * Also handles damage exchange between spiders and the player, player death
     * and respawn and the heal effect from the potions.
     */
    
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
                
            } else if(e.getReceivingBody() instanceof Spider) {                 //Spider-player collision and damage exchange
                
                if(luke.getHP() > 0) {
                    spider.damage(luke);
                } else if(luke.getLives() > 0) {
                        luke.die();                                             //Player death and respawn
                        luke.deathSFX();
                        luke.setPosition(luke.getWorld().spawnPoint());
                        luke.setHP(luke.getMaxHP());
                } else System.exit(0);
                
                if(spider.getHP() > 0) {
                    luke.damageSpider(spider);
                    System.out.println("Spider HP is "  +spider.getHP());
                } else spider.destroy();
                
            } else if(e.getReceivingBody() instanceof HPpot) {                  //Hit Points potion heals player
                hpPot.heal(luke);
            }
        }
    }
}
