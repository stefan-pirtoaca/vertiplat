package vertiplat;

import city.cs.engine.*;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class NormalView extends UserView {
    private Image backgroundImage;
    
    public NormalView (World world, int width, int height){
        super(world, width, height);
        backgroundImage = new ImageIcon ("data/background.jpg").getImage() 
                .getScaledInstance(width, height, 1);
    }
    
    @Override
    protected void paintBackground (Graphics2D g){
        g.drawImage(backgroundImage, 0,0, this);
    }
    
    @Override
    protected void paintForeground (Graphics2D g) { //left off here
        //GUI goes here
    }
}
