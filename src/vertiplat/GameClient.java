package vertiplat;

import city.cs.engine.*;

import javax.swing.JFrame;

/* The world is peaceful, for now, but some say that The Enemy is brewing great
 * Evil that threatens the innocence of Arrana as we know it.
 */

public class GameClient {

    static BaseLevel world; //the world
    private NormalView view;
    private Controller controller;
    private int level;
    
    //Constructor
    public GameClient() {
    
        //make world
        world = new Level1();
        world.build(this);
        level = 1;
        
        //user view
        view = new NormalView(world, 800, 700);
        view.setZoom(30f);
        final JFrame frame = new JFrame("VertiPlat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        controller = new Controller(world.getPlayer());
        frame.addKeyListener(controller);
        //uncomment to enable debugging view
        //JFrame debugView = new DebugViewer(world, 800, 700);

        world.start();
        world.addStepListener(new Tracker(view, world.getPlayer()));
    }
    
    public Luke getPlayer() {
        return world.getPlayer();
    }
    
    public void goNextLevel() {
        world.setPaused(true);
        switch (level) {
            case 1: {
            level++;
            world = new Level2();
            world.build(this);
            controller.setBody(world.getPlayer());
            view.setWorld(world);
            world.start();
            break;
            }
            
            case 2: {
            level++;
            world = new Level3();
            world.build(this);
            controller.setBody(world.getPlayer());
            view.setWorld(world);
            world.start();
            break;
            }
            
            case 3: 
                System.exit(0);
        }
    }
    
     public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }
    
    public static void main(String[] args) {
        new GameClient();
        new SpiderWalking(world.getSpider());
    }
}