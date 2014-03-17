package vertiplat;

import city.cs.engine.*;

public class Spider extends DynamicBody {
    
    private static final Shape          bodyShape = new BoxShape(2, 0.8f);
    private static final BodyImage      spiderRight = new BodyImage("data/spiderRight.png", 2f);
    private static final int            damage = 10;
    private static final float          walkSpeed = 2f;
    private final BaseLevel             world;
    private int                         HP = 20;
    private GoldCoin                    coin;    
    private HPpot                       hpPot;    

    public Spider(BaseLevel world) {
        super(world, bodyShape);
        this.world = world;
        SolidFixture body = new SolidFixture(this, bodyShape);
        setImage(spiderRight);
        setFixedRotation(true);
        body.setFriction(30);
        body.setRestitution(0);
        this.addCollisionListener(new CollisionHandler(world.getPlayer(), this,
                                  coin, hpPot));
        world.addStepListener(new EnemyWalker(this, walkSpeed));
     }
    
    public void damage(Luke luke) {
        luke.setHP(luke.getHP() - damage);
    }
       
    public int getHP() {
        return HP;
    }
    
    public void setHP(int HP) {
        this.HP = HP;
    }
}
