import java.awt.*;
import java.awt.image.BufferedImage;
public class Frog extends Sprite{
    public Frog(BufferedImage image, Point location){
        super(image, location);
    }
    public void moveLeft(){move(-getHeight(), 0);
    setImage(Resources.frog_left);}
    public void moveRight(){move(getHeight(), 0);
        setImage(Resources.frog_right);}
    public void moveUp(){;move(0,-getHeight());
        setImage(Resources.frog_up);}
    public void moveDown(){move(0,getHeight());
        setImage(Resources.frog_down);}
}
