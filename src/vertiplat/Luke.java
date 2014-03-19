package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Luke extends DynamicBody {
    
    //The Player
    
    private static final Shape      bodyShape = new BoxShape(1, 1.8f, new Vec2(0, 0));
    private static final Shape      armRight = new BoxShape(1.5f, 0.3f, new Vec2(1.75f, -0.2f)); //x was 0.75f
    private static final Shape      armLeft = new BoxShape(1.5f, 0.3f, new Vec2(-1.75f, -0.2f));
    private static final BodyImage  walkRight = new BodyImage("data/LukeRight.png", 4f);
    private static final BodyImage  attackingLeftImg = new BodyImage("data/LukeAttackingLeft.png", 4f);
    private static final BodyImage  attackingRightImg = new BodyImage("data/LukeAttackingRight.png", 4f);
    private final BaseLevel         world;
    private SolidFixture            swordArm;
    private int                     lives = 3;
    private static final float      maxHP = 100;
    private float                   HP;
    private static final int        swordDamage = 5;                            //damage when attacking
    private static final int        baseDamage = 0;                             //damage when not attacking
    private int                     damage;                                     //value of the damage in the current attacking state
    private int                     gold = 0;
    
    public Luke(BaseLevel world) {
        super(world, bodyShape);
        this.world = world;
        damage = baseDamage;
        HP = maxHP;
        SolidFixture body = new SolidFixture(this, bodyShape);
        body.setFriction(100);
        body.setRestitution(0);
        setFixedRotation(true);
        setImage(walkRight);
    }
    
    @Override
    public BaseLevel getWorld() {                                               //overriden this because I need access to the spawnPoint method,
        return world;                                                           //for respawning
    }
    
    public void attackLeft(Luke body) {
        swordArm = new SolidFixture(this, armLeft);
        body.setImage(attackingLeftImg);
        this.setAttackDamage(swordDamage);
    }
    
    public void attackRight(Luke body) {
        swordArm = new SolidFixture(this, armRight);
        body.setImage(attackingRightImg);
        this.setAttackDamage(swordDamage);
    }
    
    public void setAttackDamage(int damage) {
        this.damage = damage;
    }
    
    public void endAttack() {
        swordArm.destroy();
        this.setAttackDamage(baseDamage);
    }
    
    public void damageSpider(Spider spider) {
         spider.setHP(spider.getHP() - damage);
    }
    
    public int getLives() {
        return lives;
    }

    public void die() {
        lives--;
    }
    
    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }
    
    public float getMaxHP() {
        return maxHP;
    }
    
    public void increaseGold(int amount) {
        gold += amount;
    }
    
    public int getGold() {
        return gold;
    }
}
