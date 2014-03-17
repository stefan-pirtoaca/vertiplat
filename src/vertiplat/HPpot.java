package vertiplat;

import city.cs.engine.*;

public class HPpot extends DynamicBody{
    
    private static final Shape shape = new BoxShape(0.5f, 0.5f);
    private final BodyImage image = new BodyImage("data/HPpot.png");
    private static final int heal = 10;
    private GoldCoin goldCoin;
    private Spider spider;
    
    public HPpot(BaseLevel world) {
        super(world, shape);
        setImage(image);
        this.addCollisionListener(new CollisionHandler(world.getPlayer(), spider, goldCoin, this));
    }
    
    public void heal(Luke luke) {
        luke.setHP(luke.getHP() + heal);
    }   
}
