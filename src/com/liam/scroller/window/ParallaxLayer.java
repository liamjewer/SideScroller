package com.liam.scroller.window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ParallaxLayer {
    private BufferedImage texture;
    private int x, y;
    private int width, height;
    private int dx;
    private int gap;
    private boolean left, right;

    public ParallaxLayer(BufferedImage texture, int dx, int gap){
        this.texture = texture;
        this.dx = dx;
        this.gap = gap;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.x = this.y = 0;
    }

    public ParallaxLayer(BufferedImage texture, int dx){
        this(texture, dx, 0);
    }

    public void setLeft() {
        right = false;
        left = true;
    }

    public void setRight() {
        right = true;
        left = false;
    }

    public void stop(){
        right = left = false;
    }

    public void move(){
        if(right) x = (x + dx) % (width + gap);
        else x = (x - dx) % width;
    }

    public void render(Graphics2D g2d){
        /*if(x == 0)
            Game.renderParalax(g2d, 0, ObjectHandler.maxX * 32, 0, ObjectHandler.maxX * 32, y, texture);
        else if(x < 0 && x < ObjectHandler.maxX * 32) {
            Game.renderParalax(g2d, x, ObjectHandler.maxX * 32, 0, Game.WIDTH - x, y, texture);
            Game.renderParalax(g2d, 0, x, width - x, width, y, texture);
        }else if(x <= ObjectHandler.maxX * 32)
            Game.renderParalax(g2d, 0, ObjectHandler.maxX * 32, width - x, width - x + ObjectHandler.maxX * 32, y, texture);
        else if(x < 0 && x >= ObjectHandler.maxX * 32 - width)
            Game.renderParalax(g2d, 0, ObjectHandler.maxX * 32, -x, ObjectHandler.maxX * 32 - x, y, texture);
        else if(x < ObjectHandler.maxX * 32 - width){
            Game.renderParalax(g2d, 0, width + x, -x, width, y, texture);
            Game.renderParalax(g2d, gap + width + x, gap + ObjectHandler.maxX * 32, 0, ObjectHandler.maxX * 32 - width - x, y, texture);
        }*/
        for(int i = 0; i < ObjectHandler.maxX * 32; i += width){
            g2d.drawImage(texture, i, 0, null);
        }
    }
}
