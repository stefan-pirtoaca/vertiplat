package vertiplat;

import city.cs.engine.*;
import java.applet.*;
import org.jbox2d.common.Vec2;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Luke extends DynamicBody {
    
    //The Player
    
    private static final Shape      bodyShape = new BoxShape(1, 1.8f, new Vec2(0, 0));
    private static final Shape      armRight = new BoxShape(1.5f, 0.3f, new Vec2(1.75f, -0.2f)); //x was 0.75f
    private static final Shape      armLeft = new BoxShape(1.5f, 0.3f, new Vec2(-1.75f, -0.2f));
    private static final BodyImage  walkRight = new BodyImage("data/LukeRight.png", 4f);
    private static final BodyImage  attackingLeftImg = new BodyImage("data/LukeAttackingLeft.png", 4f);
    private static final BodyImage  attackingRightImg = new BodyImage("data/LukeAttackingRight.png", 4f);
    private static final File       swing1File = new File("data/sfx/swing.wav");
    private static final File       swing2File = new File("data/sfx/swing2.wav");
    private static final File       jumpSoundFile = new File("data/sfx/jump.wav");
    private static final File       deathSoundFile = new File("data/sfx/death.wav");
    private final AudioClip         swing1;
    private final AudioClip         swing2;
    private final AudioClip         jumpSFX;
    private final AudioClip         deathSFX;
    private final BaseLevel         world;
    private SolidFixture            swordArm;
    private int                     lives = 3;
    private static final float      maxHP = 100;
    private float                   HP;
    private static final int        swordDamage = 5;                            //damage when attacking
    private static final int        baseDamage = 0;                             //damage when not attacking
    private int                     damage;                                     //value of the damage in the current attacking state
    private int                     gold = 0;
    private static final File   menuBGMFile = new File("/data/MenuBGM.wav");
    private static AudioClip    menuBGM;
    
    public Luke(BaseLevel world) throws MalformedURLException {
        super(world, bodyShape);
        this.world = world;
        damage = baseDamage;
        HP = maxHP;
        SolidFixture body = new SolidFixture(this, bodyShape);
        body.setFriction(100);
        body.setRestitution(0);
        setFixedRotation(true);
        setImage(walkRight);
        
        //sound files get converted to URLs
        URL swing1URL = swing1File.toURI().toURL();                             
        URL swing2URL = swing2File.toURI().toURL();
        URL jumpSFXURL = jumpSoundFile.toURI().toURL();
        URL deathSFXURL = deathSoundFile.toURI().toURL();
        //AudioClip variables initialisations, these hold the actual sound clips for various sfx
        swing1 = Applet.newAudioClip(swing1URL);
        swing2 = Applet.newAudioClip(swing2URL);
        deathSFX = Applet.newAudioClip(deathSFXURL);
        jumpSFX = Applet.newAudioClip(jumpSFXURL);
        
        URL menuBGM_URL = menuBGMFile.toURI().toURL();
        menuBGM = Applet.newAudioClip(menuBGM_URL);
    }
    
    @Override
    public BaseLevel getWorld() {                                               //overriden this because I need access to the spawnPoint method,
        return world;                                                           //for respawning
    }
    
    public void attackLeft(Luke body) {
        swordArm = new SolidFixture(this, armLeft);
        body.setImage(attackingLeftImg);
        setAttackDamage(swordDamage);
        swingSFX();
    }
    
    public void attackRight(Luke body) {
        swordArm = new SolidFixture(this, armRight);
        body.setImage(attackingRightImg);
        setAttackDamage(swordDamage);
        swingSFX();
    }
    
    public void setAttackDamage(int damage) {
        this.damage = damage;
    }
    
    public void endAttack() {
        swordArm.destroy();
        setAttackDamage(baseDamage);
    }
    
    public void damageSpider(Spider spider) {
         spider.setHP(spider.getHP() - damage);
    }
    
    /*
     *Makes a swing sound, randomly choses one of the two sfx.
     */
    private void swingSFX() {
        Random attackSound = new Random();
        if(attackSound.nextBoolean()) {
            swing1.play();
        } else swing2.play();
    }
    
    public void jumpSFX() {
        menuBGM.play();
    }
    
    public void deathSFX() {
        deathSFX.play();
    }
    
    public int getLives() {
        return lives;
    }

    public void die() {
        lives--;
    }
    
    public void setLives(int lives) {
        this.lives = lives;
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
