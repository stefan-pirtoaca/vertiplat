package vertiplat;

import city.cs.engine.DebugViewer;
import java.awt.BorderLayout;

import javax.swing.JFrame;

    /* 
     *
     */

public class GameClient {

    private static BaseLevel    world;                                          //the world
    private final NormalView    view;
    private final Controller    controller;
    private int                 level;
    
    //Constructor
    public GameClient() {
    
        //make world
        world = new Level1();
        world.build(this);
        level = 1;
        
        //user view
        view = new NormalView(world, 800, 700);
        view.setZoom(25f);
        final JFrame frame = new JFrame("VertiPlat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        controller = new Controller(world.getPlayer());
        frame.addKeyListener(controller);
        JFrame debugView = new DebugViewer(world, 800, 700);                  //uncomment to enable debugging view

        world.start();
        world.addStepListener(new Tracker(view, world.getPlayer()));
        
        //GUI GUIcontrols = new GUI(world);
        //frame.add(GUIcontrols, BorderLayout.NORTH);
    }
    
    public Luke getPlayer() {
        return world.getPlayer();
    }
    
    public void goNextLevel() {
        world.setPaused(true);
        switch (level) {
            case 1: 
                level++;
                float oldHP = this.getPlayer().getHP();
                int lives = this.getPlayer().getLives();
                world = new Level2();
                world.build(this);
                world.getPlayer().setHP(oldHP);
                world.getPlayer().setLives(lives);
                controller.setBody(world.getPlayer());
                view.setWorld(world);
                view.setZoom(25f);
                world.addStepListener(new Tracker(view, world.getPlayer()));
                world.start();
            break;
            
            case 2:
                level++;
                float oldHP1 = this.getPlayer().getHP();
                int lives1 = this.getPlayer().getLives();
                world = new Level3();
                world.build(this);
                world.getPlayer().setHP(oldHP1);
                world.getPlayer().setLives(lives1);
                controller.setBody(world.getPlayer());
                view.setWorld(world);
                view.setZoom(25f);
                world.addStepListener(new Tracker(view, world.getPlayer()));
                world.start();
            break;
                
            case 3: 
                System.exit(0);
        }
    }
    
     public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }
    
    public static void main(String[] args) {
        new GameClient();
    }
}
