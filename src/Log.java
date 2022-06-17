import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Log extends Sprite{

    private int direction;

    ArrayList<BufferedImage> logParts = new ArrayList<>();

    public Log(BufferedImage image, Point location, int direction, int length) {
        super(image, location);
        this.direction = direction;
        for (int i = 0; i < length; i++) {
            if(i == 0)
                logParts.add(Resources.log_left);
            else if(i == length - 1)
                logParts.add(Resources.log_right);
            else
                logParts.add(Resources.log_middle);
        }
    }
    public void move(){
        move(direction, 0);
    }

    @Override
    public boolean intersects(Sprite other) {

        int w = 0;
        for (int i = 0; i < logParts.size(); i++)
            w += logParts.get(i).getWidth();
        Rectangle hitBox = new Rectangle(getX(), getY(), w, getHeight());
        Rectangle otherHitBox = new Rectangle(other.getX(), other.getY(), other.getWidth(), other.getHeight());
        return hitBox.intersects(otherHitBox);
    }

    @Override
    public void draw(Graphics2D g2){
        for (int i = 0; i < logParts.size(); i++) {
            g2.drawImage(logParts.get(i), getX() + i * 26, getY(), null);
        }
    }

    public int getDirection(){
        return direction;
    }
}
