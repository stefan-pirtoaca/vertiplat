/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vertiplat;

import city.cs.engine.*;

/**
 *
 * @author stefan
 */
public class ExitListener implements CollisionListener{
    
    private final GameClient game;
    private Luke luke;

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
