package ca.Liam.games;

import ca.Liam.audio.audioHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class Snake implements ActionListener, KeyListener {

    private RenderPanel renderpanel;
    static Snake snake;
    private Timer timer = new Timer(20, this);
    ArrayList<Point> snakeParts = new ArrayList<>();
    private static final int UP = 0, DOWN = 1, LEFT =2,RIGHT = 3;
    static final int SCALE = 10;
    private int  ticks = 0;
    private int direction = DOWN;
    private int spode;
    int score, tailLength;
    Point head, cherry;
    private Random random;
    private boolean once;
    boolean over = false, paused;


    private Snake(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen_Size = toolkit.getScreenSize();
        renderpanel = new RenderPanel();
        audioHandler.loopSound("res/background.wav");
        JFrame jframe = new JFrame("Snake");
        jframe.setSize(815, 710);
        jframe.setLocation(screen_Size.width / 2 - jframe.getWidth() / 2, screen_Size.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderpanel);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        paused = true;
        RenderPanel.message = "click space to play";
        StartGame();
    }

    private void StartGame(){
        spode = 3;
        over = false;
        once = true;
        score = 0;
        tailLength = 2;
        direction = DOWN;
        head = new Point(0, -1);
        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(79), random.nextInt(66));
        for (int i = 0; i < tailLength; i++){
            snakeParts.add(new Point(head.x, head.y));
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        renderpanel.repaint();
        ticks++;

        if (snake.over) {
            RenderPanel.message = "Game Over click space to replay";
            if (once) {
                audioHandler.playSound("res/over.wav");
                once = false;
            }
        }

        if(ticks % spode == 0 && head != null && !over && !paused) {
            snakeParts.add(new Point(head.x, head.y));
            if (direction == UP) {
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    over = true;
                }
            }
            if (direction == DOWN) {
                if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                } else {
                    over = true;
                }
            }
            if (direction == LEFT) {
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    over = true;
                }
            }
            if (direction == RIGHT) {
                if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    over = true;
                }
            }
            if (snakeParts.size() > tailLength) {
                snakeParts.remove(0);
            }
            if (cherry != null){
                if (head.equals(cherry)){
                    score += 10;
                    tailLength++;
                    if (score != 0 && score % 500 == 0 && spode - 1 != 0.0){
                        spode --;
                        audioHandler.playSound("res/speed.wav");
                    }
                    boolean goodpos = false;
                    while (!goodpos) {
                        int ranX = random.nextInt(79);
                        int ranY = random.nextInt(66);
                        if (ranX - head.x > 10 || head.x - ranX > 10
                                || ranY - head.y > 10 || head.y - ranY > 10) {
                            audioHandler.playSound("res/spawn.wav");
                            cherry.setLocation(ranX, ranY);
                            goodpos = true;
                        }
                    }
                }
            }
        }
    }

    private boolean noTailAt(int x, int y){
        for (Point point: snakeParts){
            if (point.equals(new Point(x, y))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        snake = new Snake();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_W && direction != DOWN){
            direction = UP;
        }else if (i == KeyEvent.VK_A && direction != RIGHT){
            direction = LEFT;
        }else if (i == KeyEvent.VK_S && direction != UP){
            direction = DOWN;
        }else if (i == KeyEvent.VK_D && direction != LEFT){
            direction = RIGHT;
        }
        if (i == KeyEvent.VK_SPACE){
            RenderPanel.message = "paused click space to resume";
            if (over){
                StartGame();
            }else{
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}