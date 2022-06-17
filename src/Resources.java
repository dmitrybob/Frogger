import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class Resources {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.


    public static BufferedImage frog_up, frog_left, frog_right, frog_down, car_1, car_2, car_3, car_4, car_5, log_left,
    log_middle, log_right;

    static{
        try{
            frog_up = ImageIO.read(new File("./res/frog_up.png"));
            frog_left = ImageIO.read(new File("./res/frog_left.png"));
            frog_right = ImageIO.read(new File("./res/frog_right.png"));
            frog_down = ImageIO.read(new File("./res/frog_down.png"));
            car_1 = ImageIO.read(new File("./res/Car1.png"));
            car_2 = ImageIO.read(new File("./res/Car2.png"));
            car_3 = ImageIO.read(new File("./res/Car3.png"));
            car_4 = ImageIO.read(new File("./res/Car4.png"));
            car_5 = ImageIO.read(new File("./res/Car5.png"));
            log_left  = ImageIO.read(new File("./res/log_left.png"));
            log_middle  = ImageIO.read(new File("./res/log_middle.png"));
            log_right  = ImageIO.read(new File("./res/log_right.png"));
        }catch(Exception e){e.printStackTrace();}
    }
}