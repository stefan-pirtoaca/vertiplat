package vertiplat;

import city.cs.engine.*;

public class Spider extends DynamicBody {
    
    private static final Shape          bodyShape = new BoxShape(2, 0.8f);
    private final BodyImage             spiderRight = new BodyImage("data/spiderRight.png", 2f);
    private final BodyImage             spiderLeft = new BodyImage("data/spiderLeft.png", 2f);
    private static final int            damage = 2;
    private static final float          walkSpeed = 2f;          
    private int                         HP = 40;
    private GoldCoin                    coin;    
    private HPpot                       hpPot;    

    public Spider(BaseLevel world, int timeToWalk) {
        super(world, bodyShape);
        SolidFixture body = new SolidFixture(this, bodyShape);
        setImage(spiderRight);
        setFixedRotation(true);
        body.setFriction(30);
        body.setRestitution(0);
        this.addCollisionListener(new CollisionHandler(world.getPlayer(), this, coin, hpPot));
        world.addStepListener(new EnemyWalker(this, walkSpeed, timeToWalk));
     }
    
    public void damage(Luke luke) {
        luke.setHP(luke.getHP() - damage);
    }
    
    public void switchImage(String s) {
        switch (s) {
            case "right":
                this.setImage(spiderRight);
                break;
            case "left":
                this.setImage(spiderLeft);
                break;
            default:
                System.err.println("switchImage in Spider needs right or left as input");
        }
    }
       
    public int getHP() {
        return HP;
    }
    
    public void setHP(int HP) {
        this.HP = HP;
    }
}
