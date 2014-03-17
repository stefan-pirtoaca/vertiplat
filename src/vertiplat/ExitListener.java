package vertiplat;

import city.cs.engine.*;

public class ExitListener implements CollisionListener{
    
    private final GameClient    game;
    private Luke                luke;

    public ExitListener(GameClient game) {
        this.game = game;
    }
    
    @Override
    public void collide(CollisionEvent e) {
         luke = game.getPlayer();
         if (e.getOtherBody() == luke && game.isCurrentLevelCompleted()) {
            System.out.println("Going to next level...");
            game.goNextLevel();
        }
    }
}
