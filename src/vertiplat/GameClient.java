package vertiplat;

import city.cs.engine.DebugViewer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;

/* 
 *
 */
public class GameClient {

    //menuBGM is "Menu Theme" by Axton Dylan Crolley http://opengameart.org/content/menu-or-level-select-theme
    //shortened version
    //used under the CC-BY 3.0 License
    private static final File   menuBGMFile = new File("/data/MenuBGM.wav");
    //gameBGM is "Castlecall" by Alexandr Zhelanov http://opengameart.org/content/castlecall
    //used under the CC-BY 3.0 License
    private static final File   gameBGMFile = new File("/data/sfx/gameBGM.wav");
    private static final float  ZOOM_FACTOR = 25f;
    private static BaseLevel    world;
    private static MainMenu     menu;
    private static Options      options;
    private static NormalView   view;
    private static Controller   controller;
    private static AudioClip    menuBGM;
    private static AudioClip    gameBGM;
    private static boolean      debugViewEnabled = false;
    private final JFrame        frame;
    private JFrame              debugView;
    private int                 level;

    public GameClient() throws MalformedURLException, IOException {

        frame = new JFrame("VertiPlat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        menu = new MainMenu(this);
        frame.add(menu);
        frame.pack();
        frame.setVisible(true);
        options = new Options(this);

        URL menuBGM_URL = menuBGMFile.toURI().toURL();
        menuBGM = Applet.newAudioClip(menuBGM_URL);
        URL gameBGM_URL = gameBGMFile.toURI().toURL();
        gameBGM = Applet.newAudioClip(gameBGM_URL);
    }

    public final void newGame() throws MalformedURLException {
        menuBGM.stop();
        gameBGM.loop();
        level = 1;
        world = new Level1();
        world.build(this);
        view = new NormalView(world, 800, 700);
        view.setZoom(ZOOM_FACTOR);
        if(debugViewEnabled) {
            debugView = new DebugViewer(world, 800, 700);
        }
        frame.remove(menu);
        frame.add(view);
        frame.setResizable(false);
        frame.setVisible(true);
        controller = new Controller(world.getPlayer());
        frame.addKeyListener(controller);
        world.addStepListener(new Tracker(view, world.getPlayer()));
        world.start();
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
        if(debugViewEnabled) {
            debugView.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            debugView = new DebugViewer(world, 800, 700);
        }
        view.setWorld(world);
        view.setZoom(ZOOM_FACTOR);
        view.updatePlayer(world.getPlayer());
        world.addStepListener(new Tracker(view, world.getPlayer()));
        world.start();
    }

    public void goNextLevel() throws MalformedURLException {
        world.setPaused(true);
        switch (++level) {
            case 2:
                float oldHP = getPlayer().getHP();
                int lives = getPlayer().getLives();
                startLevel(level, oldHP, lives);
                break;
            case 3:
                float oldHP1 = getPlayer().getHP();
                int lives1 = getPlayer().getLives();
                startLevel(level, oldHP1, lives1);
                break;
            default:
                System.exit(0);
        }
    }

    public Luke getPlayer() {
        return world.getPlayer();
    }

    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }
    
    public void menuNavigation(String s) {
        switch (s) {
            case "options":
                frame.remove(menu);
                frame.add(options);
                frame.setVisible(true);
                frame.repaint();
                break;
            case "back":
                frame.remove(options);
                frame.add(menu);
                frame.setVisible(true);
                frame.repaint();
                break;
        }
    }
    
    public void setMusic(boolean music) {
        if (music) {
            menuBGM.loop();
            gameBGM.loop();
        }
    }
    
    public void setDebuggerView(boolean debuggerView) {
        if (debuggerView) {
            debugViewEnabled = true;
        }
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        new GameClient();
    }
}
