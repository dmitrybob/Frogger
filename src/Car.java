import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

public class Car extends Sprite{
    private int direction;
    public Car(BufferedImage image, Point location, int direction){
        super(image, location);
        this.direction = direction;
    }
    public void move(){
        move(direction * 5, 0);
    }
}
