package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Luke extends DynamicBody {
    
    //The Player
    
    private static final Shape bodyShape = new BoxShape(1, 1.8f, new Vec2(0, 0));
    private static final Shape armRight = new BoxShape(1.5f, 0.3f, new Vec2(1.75f, 0)); //x was 0.75f
    private static final Shape armLeft = new BoxShape(1.5f, 0.3f, new Vec2(-1.75f, 0));
    private static final BodyImage walkRight = new BodyImage("data/LukeRight.png", 4f);
    private static final BodyImage attackingLeftImg = new BodyImage("data/LukeAttackingLeft.png", 4f);
    private static final BodyImage attackingRightImg = new BodyImage("data/LukeAttackingRight.png", 4f);
    private int HP = 100;
    private int baseDamage = 5;
    private SolidFixture swordArm;
    
    private int gold = 0;
    
    public Luke(BaseLevel world) {
        super(world, bodyShape);
        SolidFixture body = new SolidFixture(this, bodyShape);
        body.setFriction(100);
        body.setRestitution(0);
        setFixedRotation(true);
        setImage(walkRight);
    }
    
    public void attackLeft(Luke body) {
        swordArm = new SolidFixture(this, armLeft);
        body.setImage(attackingLeftImg);
    }
    
    public void attackRight(Luke body) {
        swordArm = new SolidFixture(this, armRight);
        body.setImage(attackingRightImg);
    }
    
    public void endAttack() {
        swordArm.destroy();
    }
    
    public void damageSpider(Spider spider) {
        spider.setHP(spider.getHP() - baseDamage);
    }
    
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    
    public void increaseGold(int amount) {
        gold += amount;
    }
    
    public int getGold() {
        return gold;
    }
}
