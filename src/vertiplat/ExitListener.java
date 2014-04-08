package vertiplat;

import city.cs.engine.*;
import java.net.MalformedURLException;

public class ExitListener implements CollisionListener {

    private final GameClient    game;
    private final LevelExit     exit;
    private final Luke          luke;

    public ExitListener(GameClient game, LevelExit exit) {
        this.game = game;
        this.exit = exit;
        luke = game.getPlayer();
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == luke && game.isCurrentLevelCompleted()) {
            exit.exitSFX();
            try {
                game.goNextLevel();
            } catch (MalformedURLException ex) {
                System.err.println("MalformedURLException, class ExitListener line 25");
            }
        }
    }
}
