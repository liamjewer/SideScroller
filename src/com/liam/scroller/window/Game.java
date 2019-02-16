package com.liam.scroller.window;

import com.liam.scroller.framework.*;
import com.liam.scroller.objects.HUD;
import com.liam.scroller.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static boolean GameOver = false, Won = false;
    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;

    public static ObjectHandler handler;
    Camera cam;
    static Texture texture;
    public static State state = State.Menu;
    GameMenu menu = new GameMenu();

    Random r = new Random();

    public static int LEVEL = 1;

    private void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        texture = new Texture();
        handler = new ObjectHandler(cam);
        cam = new Camera(0, 0);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput());
    }

    public synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(new Color(153, 204, 255));
        g.fillRect(0, 0, getWidth(), getHeight());

        if(state == State.Game) {
            //////////////////////////////////
            g2d.translate(cam.getX(), cam.getY()); //begin of cam
            //everything between here is effected by the camera movement
            handler.render(g);

            g2d.translate(-cam.getX(), -cam.getY()); //end of cam
            //////////////////////////////////
            HUD.render(g);
            if (GameOver) {
                //center this text
                g.setColor(Color.RED);
                g.setFont(new Font("game over", Font.BOLD, 100));
                g.drawString("Game over!", WIDTH / 2 - g.getFontMetrics().stringWidth("Game over!") / 2, 300);
                g.setColor(new Color(200, 0,0));
                g.setFont(new Font("game over", Font.BOLD, 20));
                g.drawString("You made it to level " + LEVEL + " and picked up " + Player.Points + " Coins", WIDTH / 2 - g.getFontMetrics().stringWidth("You made it to level: " + LEVEL + " and picked up " + Player.Points + " Coins") / 2, 340);
            }else if (Won) {
                //center this text
                g.setColor(Color.RED);
                g.setFont(new Font("You win", Font.BOLD, 100));
                g.drawString("You Won!", WIDTH / 2 - g.getFontMetrics().stringWidth("Game over!") / 2, 300);
                g.setColor(new Color(200, 0,0));
                g.setFont(new Font("winner", Font.BOLD, 20));
                g.drawString("You made it to level " + LEVEL + " and picked up " + Player.Points + " Coins", WIDTH / 2 - g.getFontMetrics().stringWidth("You made it to level: " + LEVEL + " and picked up " + Player.Points + " Coins") / 2, 340);
            }
        }else if(state == State.Menu){
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    private void tick() {
        if(state == State.Game) {
            handler.tick();
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ObjectId.Player) {
                    cam.tick(tempObject);
                }
            }
        }
    }

    public static Texture getInstance(){
        return texture;
    }

    public static void main(String[] args){
        new Window(800, 608, "Side Scroller", new Game());
    }
}
