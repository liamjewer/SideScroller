package com.liam.scroller.framework;

import java.awt.*;

public class PausedMenu {
    static Button resume;
    static Button menu;
    static Button exit;

    public PausedMenu(){
        resume = new Button(250, 100, 300, 100, true, "Resume", Color.GREEN, Color.WHITE, ButtonID.Play);
        menu = new Button(250, 250, 300, 100, true, "Main Menu", Color.BLUE, Color.WHITE, ButtonID.Controls);
        exit = new Button(250, 400, 300, 100, true, "Exit", Color.MAGENTA, Color.WHITE, ButtonID.Controls);
    }

    public void render(Graphics g){
        resume.render((Graphics2D) g);
        menu.render((Graphics2D) g);
        exit.render((Graphics2D) g);
    }
}