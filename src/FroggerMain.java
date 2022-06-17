import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FroggerMain extends JPanel {

    private Timer timer;

    private Frog frog; // I haven't made a Frog class yet, but you should, and change this type to Frog

    private ArrayList<Car> cars = new ArrayList();

    ArrayList<Log> logs = new ArrayList();

    private int waterX, waterPosX, waterY, waterPosY;

    private boolean inWater = false;

    private static int level = 1;

    private int hp = 3;

    private boolean alive = true;

    private boolean win = false;

    public FroggerMain(int w, int h){
        setSize(w, h);
        frog = new Frog(Resources.frog_up, new Point(300, 550));
        timer = new Timer(1000/60, e->update());
        timer.start();
        waterX = 800;
        waterY = 100;
        waterPosX = 0;
        waterPosY = 100;
        setupKeyListener();
        for(int i = 0; i < 10; i++){
            int x = 240;
            while(true){
                x += i * 30;
                if(x >= 540)
                    x = 240;
                if(x < 80 || x > waterY + waterPosY)
                    break;
            }
            if(Math.random() < .2)
                cars.add( new Car(Resources.car_1, new Point((int)(Math.random()*600), x), -1));
            else if(Math.random() < .4)
                cars.add( new Car(Resources.car_2, new Point((int)(Math.random()*600), x), 1));
            else if(Math.random() < .6)
                cars.add( new Car(Resources.car_3, new Point((int)(Math.random()*600), x), -1));
            else if(Math.random() < .8)
                cars.add( new Car(Resources.car_4, new Point((int)(Math.random()*600), x), 1));
            else
                cars.add(new Car(Resources.car_5 , new Point((int)(Math.random()*600), x), -1));
        }
        for(int i = 0; i < 5; i++){
            int length = (int)(Math.random() * 4 + 2);
            int y = 100 + i * 20;
            if(Math.random() < .5)
                logs.add(new Log( Resources.car_1, new Point((int)(Math.random()* 600), y), -1, length));
            else
                logs.add(new Log( Resources.car_1, new Point((int)(Math.random()* 600), y), 1, length));
        }

    }

    // called every frame (60 times per second by default)
    public void update(){
        // update any time-based changes here
        for(int i = 0 ; i < cars.size(); i++){
            cars.get(i).move();
            if(cars.get(i).getX() > 600)
                cars.get(i).move(-600,0);
            if(cars.get(i).getX() < 0)
                cars.get(i).move(600,0);
            if(cars.get(i).intersects(frog)) {
                frog.move(300 - frog.getX(), 550 - frog.getY());
                hp--;
            }


        }
        for(int i = 0; i < logs.size(); i++){
            logs.get(i).move();
            if(logs.get(i).getX() > 600)
                logs.get(i).move(-600, 0);
            if(logs.get(i).getX() < 0)
                logs.get(i).move(600, 0);

        }

        if(frog.getY() > waterY && frog.getY() < waterY + waterPosY) {
            inWater = true;
            for(int i = 0; i < logs.size(); i++) {
                if (logs.get(i).intersects(frog)) {
                    inWater = false;
                    frog.move(logs.get(i).getDirection(),0);
//                    System.out.println(logs.get(i).getDirection());
                }
            }
            if(inWater) {
                frog.move(300 - frog.getX(), 550 - frog.getY());
                hp--;
            }
        }

        if(hp == 0) {
            alive = false;
            frog.move(300 - frog.getX(), 550 - frog.getY());
        }
        if(frog.getY() < 100) {
            alive = false;
            frog.move(300 - frog.getX(), 550 - frog.getY());
            level++;
            win = true;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        // all drawing happens here.
        // Best practice is to NOT change the state of any instance fields
        // so the graphics can update at any time
        setBackground(Color.BLACK);

        g2.setColor(Color.BLUE);

        g2.fill(new Rectangle(waterPosX, waterPosY, waterX , waterY));

        Color color = new Color(63,13, 94);

        g2.setColor(color);

        g2.fill(new Rectangle(0,540, 600, 60));

        g2.fill(new Rectangle(0,200, 600, 40));

        Color secondColor = new Color(1,50, 32);

        g2.setColor(secondColor);

        g2.fill(new Rectangle(0,0, 600, 100));

        for(int i = 0 ; i < logs.size(); i++){
            logs.get(i).draw(g2);
        }
        for(int i = 0 ; i < cars.size(); i++){
            cars.get(i).draw(g2);
        }
        g2.setColor(Color.white);

        g2.drawString("HP:" + hp, 30, 30);


        if(hp <= 0) {
            g2.setFont((new Font("TimesRoman", Font.PLAIN, 50)));
            g2.drawString("YOU LOSE", 170, 300);
            g2.setFont((new Font("TimesRoman", Font.PLAIN, 25)));
            g2.drawString("Press Q to restart", 200, 400);
        }
        if(win) {
            g2.setColor(Color.WHITE);
            g2.setFont((new Font("TimesRoman", Font.PLAIN, 50)));
            g2.drawString("YOU WIN", 170, 300);
            g2.setFont((new Font("TimesRoman", Font.PLAIN, 25)));
            g2.drawString("Press Q to play next level", 170, 400);
        }
        if(alive)
            frog.draw(g2);
    }

    //
    private boolean[] keys;
    public void setupKeyListener(){
        keys = new boolean[255];
        // I'm not sure if you'll want to use the boolean[] or just
        // if statements here in the listener.
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Option 2:
//                if(e.getKeyCode() == KeyEvent.VK_A)
//                    frog.moveLeft(); // or something.


            }
            @Override
            public void keyPressed(KeyEvent e) {
                // Option 1:
                keys[e.getKeyCode()] = true;

                if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
                    if(frog.getX() > 0)
                        frog.moveLeft();
                if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
                    if(frog.getX() < getWidth()-frog.getWidth())
                        frog.moveRight();
                if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
                    if(frog.getY() < getHeight() - frog.getHeight())
                        frog.moveDown();
                if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
                    if(frog.getY() > 0)
                        frog.moveUp();
                if(e.getKeyCode() == KeyEvent.VK_Q){
                    hp = 3;
                    alive = true;
                    win = false;
                }

            }
            @Override
            public void keyReleased(KeyEvent e) {
                // Option 1:
                keys[e.getKeyCode()] = false;
            }
        });
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Frogger");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

        FroggerMain panel = new FroggerMain(600, 600);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}