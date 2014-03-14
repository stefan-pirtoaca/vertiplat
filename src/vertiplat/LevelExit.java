package vertiplat;

import city.cs.engine.*;
import java.awt.Color;

public class LevelExit extends StaticBody{
    
    private static final Shape bodyShape = new BoxShape(1, 1.5f);
    
    public LevelExit(BaseLevel world) {
        super(world, bodyShape);
        this.setFillColor(Color.green);
    }   
}
