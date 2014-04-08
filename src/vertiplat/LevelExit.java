package vertiplat;

import city.cs.engine.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class LevelExit extends StaticBody{
    
    private static final Shape      bodyShape = new BoxShape(1, 1.2f);
    private static final BodyImage  exit = new BodyImage("data/crystal.gif", 3);
    private static final File       exitSoundFile = new File("data/sfx/exit.wav");
    private final AudioClip         exitSound;

    
    public LevelExit(BaseLevel world) throws MalformedURLException {
        super(world, bodyShape);
        this.setFillColor(Color.green);
        setImage(exit);
        URL exitSoundURL = exitSoundFile.toURI().toURL();
        exitSound = Applet.newAudioClip(exitSoundURL);
    }
    
    public void exitSFX() {
        exitSound.play();
    }
}
