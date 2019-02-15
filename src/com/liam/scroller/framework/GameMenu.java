package com.liam.scroller.framework;

import java.awt.*;

public class GameMenu{
    static Button play;
    Button controls;
    static Button exit;

    public GameMenu(){
        play = new Button(250, 100, 300, 100, true, "Play", Color.GREEN, Color.BLACK, ButtonID.Play);
        controls = new Button(250, 250, 300, 100, true, "How To Play", Color.BLUE, Color.BLACK, ButtonID.Controls);
        exit = new Button(250, 400, 300, 100, true, "Exit", Color.MAGENTA, Color.BLACK, ButtonID.Controls);
    }

    public void render(Graphics g){
        play.render((Graphics2D) g);
        controls.render((Graphics2D) g);
        exit.render((Graphics2D) g);
    }
}
