package vertiplat;

import city.cs.engine.*;
import java.awt.Color;

public class LevelExit extends StaticBody{
    
    private static final Shape      bodyShape = new BoxShape(1, 1.2f);
    private static final BodyImage  exit = new BodyImage("data/crystal.gif", 3);
    
    public LevelExit(BaseLevel world) {
        super(world, bodyShape);
        this.setFillColor(Color.green);
        setImage(exit);
    }   
}
