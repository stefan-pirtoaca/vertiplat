package vertiplat;

import city.cs.engine.*;

public class HPpot extends DynamicBody{
    
    private static final Shape      shape = new BoxShape(0.5f, 0.5f);
    private static final BodyImage  image = new BodyImage("data/HPpot.png");
    private static final float      heal = 15;
    private static float            maxHP;
    private GoldCoin                goldCoin;
    private Spider                  spider;
    
    public HPpot(BaseLevel world) {
        super(world, shape);
        setImage(image);
        this.addCollisionListener(new CollisionHandler(world.getPlayer(), spider,
                                  goldCoin, this));
        maxHP = world.getPlayer().getMaxHP();
    }
    
    public void heal(Luke luke) {
        if(luke.getHP() <= maxHP - heal) {
            luke.setHP(luke.getHP() + heal);
        } else luke.setHP(maxHP);        
    }   
}
