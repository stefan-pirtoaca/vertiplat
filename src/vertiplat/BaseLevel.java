package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public abstract class BaseLevel extends World {
    
    private Luke        luke;
    private LevelExit   exit;
    
    public void build(GameClient game) {
        luke = new Luke(this);
        luke.setPosition(spawnPoint());
        exit = new LevelExit(this);
        exit.setPosition(exitPoint());
        exit.addCollisionListener(new ExitListener(game));
    }
    
    public abstract Vec2 spawnPoint();
       
    public abstract Vec2 exitPoint();
        
    public abstract boolean isCompleted();
    
    public Luke getPlayer() {
        return luke;
    }
}
