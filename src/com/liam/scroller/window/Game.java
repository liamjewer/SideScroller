package com.liam.scroller.window;

import com.liam.scroller.framework.*;
import com.liam.scroller.objects.Arm;
import com.liam.scroller.objects.HUD;
import com.liam.scroller.objects.Player;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;

    public static ObjectHandler handler;
    public static Camera cam;
    static Texture texture;
    public static State state = State.Menu;
    GameMenu menu = new GameMenu();
    ControlsMenu cMenu = new ControlsMenu();
    PausedMenu pMenu = new PausedMenu();
    LevelMenu lMenu = new LevelMenu();

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
        this.addMouseMotionListener(new MouseInput());
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

        Point2D start = new Point2D.Float(WIDTH/2, 0);
        Point2D end = new Point2D.Float(WIDTH/2, HEIGHT);
        float[] distL = {0.0f, 0.75f};
        Color[] colorsL = {new Color(100, 100, 255), new Color(153, 204, 255)};
        LinearGradientPaint pL = new LinearGradientPaint(start, end, distL, colorsL);
        ((Graphics2D) g).setPaint(pL);
        g.fillRect(0, 0, getWidth(), getHeight());

        if(state == State.Game || state == State.Paused || state == State.Winner || state == State.GameOver) {
            //////////////////////////////////
            g2d.translate(cam.getX(), cam.getY()); //begin of cam
            //everything between here is effected by the camera movement
            handler.render(g);

            g2d.translate(-cam.getX(), -cam.getY()); //end of cam
            //////////////////////////////////
            Point2D center = new Point2D.Float(WIDTH/2, HEIGHT/2);
            if(Player.health < 100){
                float radius = Player.health + (int)Math.floor(WIDTH * 0.75);
                float[] dist = { 0.5f, 1.0f };
                Color[] colors = {new Color(0,0,0,0), new Color(255,0,0,255)};
                RadialGradientPaint p =
                        new RadialGradientPaint(center, radius, dist, colors);
                g2d.setPaint(p);
                g.fillRect(0,0, getWidth(), getHeight());
            }
            HUD.render(g);
            if (state == State.GameOver) {
                g.setColor(Color.BLACK);
                g.fillRect(0, Game.HEIGHT/2 - Game.HEIGHT/4, Game.WIDTH, Game.HEIGHT/2);
                //center this text
                g.setColor(Color.RED);
                g.setFont(new Font("game over", Font.BOLD, 100));
                g.drawString("Game over!", WIDTH / 2 - g.getFontMetrics().stringWidth("Game over!") / 2, HEIGHT/2 - 100);
                g.setColor(new Color(200, 0,0));
                g.setFont(new Font("game over", Font.BOLD, 20));
                g.drawString("You made it to level " + LEVEL + " and picked up " + Player.Points + " Coins", WIDTH / 2 - g.getFontMetrics().stringWidth("You made it to level: " + LEVEL + " and picked up " + Player.Points + " Coins") / 2, HEIGHT/2);
            }else if (state == State.Winner) {
                g.setColor(Color.BLACK);
                g.fillRect(0, Game.HEIGHT/2 - Game.HEIGHT/4, Game.WIDTH, Game.HEIGHT/2);
                //center this text
                g.setColor(Color.GREEN);
                g.setFont(new Font("You win", Font.BOLD, 100));
                g.drawString("You Won!", WIDTH / 2 - g.getFontMetrics().stringWidth("Game over!") / 2, HEIGHT/2 - 100);
                g.setColor(new Color(0, 100,0));
                g.setFont(new Font("winner", Font.BOLD, 20));
                g.drawString("You made it to level " + LEVEL + " and picked up " + Player.Points + " Coins", WIDTH / 2 - g.getFontMetrics().stringWidth("You made it to level " + LEVEL + " and picked up " + Player.Points + " Coins") / 2, HEIGHT/2);
            }
            if(state == State.Paused){
                pMenu.render(g);
            }
        }else if(state == State.Menu){
            menu.render(g);
        }else if(state == State.Controls){
            handler.mid.render((Graphics2D) g);
            handler.back.render((Graphics2D) g);
            handler.front.render((Graphics2D) g);
            cMenu.render(g);
        }else if(state == State.SelectLevel){
            lMenu.render(g);
        }
        g.dispose();
        bs.show();
    }

    private void tick() {
        HEIGHT = Window.frame.getHeight();
        WIDTH = Window.frame.getWidth();
        if(state == State.Game) {
            handler.tick();
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ObjectId.Player) {
                    cam.tick(tempObject);
                }
            }
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            if(Arm.state == Arm.armState.pulled) {
                Window.frame.setCursor(toolkit.createCustomCursor(Texture.crosshair, new Point(0, 0), "img"));
            }else Window.frame.setCursor(Cursor.getDefaultCursor());
        }
    }

    public static Texture getInstance(){
        return texture;
    }

    public static void main(String[] args){
        new Window(800, 608, "Side Scroller", new Game());
    }
}
