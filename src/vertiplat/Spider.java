package vertiplat;

import city.cs.engine.*;
import java.awt.Color;
import org.jbox2d.common.Vec2;

public class Spider extends DynamicBody {
    
    private static final Shape bodyShape = new BoxShape(2, 0.8f);
    private static final BodyImage spiderRight = new BodyImage("data/spiderRight.png", 2f);
    private static final int damage = 10;
    private int HP = 20;
    private GoldCoin coin;
    private Walker spiderWalkLeft;
    private Walker spiderWalkRight;
    private BaseLevel world;
    private Vec2 walkSpeed = new Vec2(2, 0);
    

    public Spider(BaseLevel world) {
        super(world, bodyShape);
        this.world = world;
        SolidFixture body = new SolidFixture(this, bodyShape);
        setImage(spiderRight);
        setFixedRotation(true);
        body.setFriction(30);
        body.setRestitution(0);
        this.addCollisionListener(new CollisionHandler(world.getPlayer(), this, coin));
        //spiderWalkLeft = new Walker(this, - 4);
        //spiderWalkRight = new Walker(this, 4);
     }
    
    public void walk() {
        for(int i = 0; i <= 100; i++) {
            this.setLinearVelocity(walkSpeed);
        }
        for(int i = 0; i <= 100; i++) {
            this.setLinearVelocity(walkSpeed.negate());
        }
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