package vertiplat;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level3 extends BaseLevel{
    
    @Override
    public void build(GameClient game) {
        super.build(game);
    }
    
    @Override
    public Vec2 spawnPoint() {
        return new Vec2(0, 0);
    }
    
    @Override
    public Vec2 exitPoint() {
        return new Vec2(0, 20);
    }
    
    @Override
    public boolean isCompleted() {
        return true;
    }
}
