package com.liam.scroller.framework;

import com.liam.scroller.window.Game;

import java.awt.*;

public class LevelMenu {
    static Button one;
    static Button two;
    static Button three;
    static Button four;

    public LevelMenu(){
        one = new Button(0, 0, 300, 100, true, "Level 1", Color.GREEN, Color.WHITE, ButtonID.one);
        two = new Button(0, 0, 300, 100, true, "Level 2", Color.BLUE, Color.WHITE, ButtonID.two);
        three = new Button(0, 0, 300, 100, true, "Level 3", Color.MAGENTA, Color.WHITE, ButtonID.three);
        four = new Button(0, 0, 300, 100, true, "Level 4", Color.cyan, Color.WHITE, ButtonID.four);
    }

    public void render(Graphics g){
        one.x = Game.WIDTH/2 - 150;
        two.x = Game.WIDTH/2 - 150;
        three.x = Game.WIDTH/2 - 150;
        four.x = Game.WIDTH/2 - 150;

        one.y = Game.HEIGHT/2 - 300;
        two.y = Game.HEIGHT/2 - 150;
        three.y = Game.HEIGHT/2;
        four.y = Game.HEIGHT/2 + 150;

        one.render((Graphics2D) g);
        two.render((Graphics2D) g);
        three.render((Graphics2D) g);
        four.render((Graphics2D) g);
    }
}
