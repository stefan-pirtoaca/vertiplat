package vertiplat;

import java.net.MalformedURLException;
import javax.swing.JFrame;

/* 
 *
 */
public class GameClient {
    
    private static final float  ZOOM_FACTOR = 25f;    
    private static BaseLevel    world;
    private final JFrame        frame;
    private NormalView          view;
    private Controller          controller;
    private int                 level;
    
    public GameClient() throws MalformedURLException {
        
        frame = new JFrame("VertiPlat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        //JFrame debugView = new DebugViewer(world, 800, 700);                  //uncomment to enable debugging view
        
        this.newGame();

        //GUI GUIcontrols = new GUI(world);
        //frame.add(GUIcontrols, BorderLayout.NORTH);
    }
    
    public void goNextLevel() throws MalformedURLException {
        world.setPaused(true);
        switch (level) {
            case 1:
                float oldHP = this.getPlayer().getHP();
                int lives = this.getPlayer().getLives();
                this.startLevel(level, oldHP, lives);
                break;
            case 2:
                float oldHP1 = this.getPlayer().getHP();
                int lives1 = this.getPlayer().getLives();
                this.startLevel(level, oldHP1, lives1);
                break;
            case 3:                
                System.exit(0);
        }
    }
    
    public final void newGame() throws MalformedURLException {
        world = new Level1();
        world.build(this);
        view = new NormalView(world, 800, 700);
        view.setZoom(ZOOM_FACTOR);
        frame.add(view);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        controller = new Controller(world.getPlayer());
        frame.addKeyListener(controller);
        world.addStepListener(new Tracker(view, world.getPlayer()));
        world.start();
        level = 1;
    }
    
    private void startLevel(int level, float oldHP, int lives) throws MalformedURLException {
        switch (level) {
            case 1:
                world = new Level1();
                break;
            case 2:
                world = new Level2();
                break;
            case 3:
                world = new Level3();
        }
        world.build(this);
        world.getPlayer().setHP(oldHP);
        world.getPlayer().setLives(lives);
        controller.setBody(world.getPlayer());
        view.setWorld(world);
        view.setZoom(ZOOM_FACTOR);
        world.addStepListener(new Tracker(view, world.getPlayer()));
        level++;
        world.start();
    }
    
    public Luke getPlayer() {
        return world.getPlayer();
    }
    
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }
    
    public static void main(String[] args) throws MalformedURLException {
        new GameClient();
    }
}
