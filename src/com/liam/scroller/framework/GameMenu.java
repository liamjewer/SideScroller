package com.liam.scroller.framework;

import com.liam.scroller.window.Game;

import java.awt.*;

public class GameMenu{
    static Button play;
    static Button controls;
    static Button exit;

    public GameMenu(){
        play = new Button(0, 0, 300, 100, true, "Play", Color.GREEN, Color.WHITE, ButtonID.Play);
        controls = new Button(0, 0, 300, 100, true, "How To Play", Color.BLUE, Color.WHITE, ButtonID.Controls);
        exit = new Button(0, 0, 300, 100, true, "Exit", Color.MAGENTA, Color.WHITE, ButtonID.Controls);
    }

    public void render(Graphics g){
        play.x = Game.WIDTH/2 - 150;
        controls.x = Game.WIDTH/2 - 150;
        exit.x = Game.WIDTH/2 - 150;

        play.y = Game.HEIGHT/2 - 200;
        controls.y = Game.HEIGHT/2 - 50;
        exit.y = Game.HEIGHT/2 + 100;

        play.render((Graphics2D) g);
        controls.render((Graphics2D) g);
        exit.render((Graphics2D) g);
    }
}
