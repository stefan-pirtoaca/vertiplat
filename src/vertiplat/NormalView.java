package vertiplat;

import city.cs.engine.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class NormalView extends UserView {
    private static final Color  HPbarColour = new Color(255, 0, 0);
    private static final Color  TextColour = new Color(0);
    private static final int    Offset = 20;
    private static final int    HPbarHeight = 30;
    private static final int    HPbarWidth = 200;
    private static Image        backgroundImage;
    private static Image        fullHeart;
    private static Image        emptyHeart;
    private final Luke          luke;
    private float               HPscale = 1;
    
    public NormalView (BaseLevel world, int width, int height){
        super(world, width, height);
        luke = world.getPlayer();
        backgroundImage = new ImageIcon ("data/background.jpg").getImage() 
                .getScaledInstance(width, height, 1);
        fullHeart = new ImageIcon("data/fullHeart.png").getImage()
                .getScaledInstance(HPbarHeight, HPbarHeight, 1);
        emptyHeart = new ImageIcon("data/emptyHeart.png").getImage()
                .getScaledInstance(HPbarHeight, HPbarHeight, 1);
    }
    
    @Override
    protected void paintBackground (Graphics2D g){
        g.drawImage(backgroundImage, 0,0, this);
    }
    
    @Override
    protected void paintForeground (Graphics2D g) {
        this.drawHPbar(g);
        this.drawLivesIndicator(g);
        this.drawCoinsIndicator(g);
    }
    
    private void drawHPbar(Graphics2D g) {
        HPscale = luke.getHP() / luke.getMaxHP();
        g.setColor(new Color(150, 150, 150));
        g.translate(Offset, Offset);
        g.fillRoundRect(-3, -3, HPbarWidth + 6, HPbarHeight + 6, 10, 10);
        g.setColor(HPbarColour);
        g.fillRoundRect(0, 0, (int) (HPscale * HPbarWidth), HPbarHeight, 10, 10);
        
        g.setColor(TextColour);
        g.drawString("HP: " + luke.getHP() + "/" + luke.getMaxHP(), 0, HPbarHeight -
                HPbarHeight/3);
    }
    
    private void drawLivesIndicator(Graphics2D g) {
        g.translate(Offset + HPbarWidth, 0);
        switch (luke.getLives()) {
            case 3:
                g.drawImage(fullHeart, 0, 0, this);
                g.drawImage(fullHeart, 2 * Offset, 0, this);
                g.drawImage(fullHeart, 4 * Offset, 0, this);
                break;
            case 2:
                g.drawImage(fullHeart, 0, 0, this);
                g.drawImage(fullHeart, 2 * Offset, 0, this);
                g.drawImage(emptyHeart, 4 * Offset, 0, this);
                break;
            default:
                g.drawImage(fullHeart, 0, 0, this);
                g.drawImage(emptyHeart, 2 * Offset, 0, this);
                g.drawImage(emptyHeart, 4 * Offset, 0, this);
        }
    }
    
    private void drawCoinsIndicator(Graphics2D g) {
        g.translate(6*Offset, 0);
        g.setColor(new Color(255, 0, 0));
        g.drawString("Remaining coins: " + (luke.getWorld().getGoldCoinNumber() -
                luke.getGold()), 0, HPbarHeight - HPbarHeight/3);
    }
}
