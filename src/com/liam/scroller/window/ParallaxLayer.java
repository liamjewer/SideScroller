package com.liam.scroller.window;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.objects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ParallaxLayer {
    private BufferedImage texture;
    private int width;
    private double speed;
    private int gap;
    private int velX;
    private int x;
    private ObjectHandler handler;

    public ParallaxLayer(BufferedImage texture, double speed, int gap, ObjectHandler handler){
        this.texture = texture;
        this.gap = gap;
        this.width = texture.getWidth();
        this.speed = speed;
        this.handler = handler;
    }

    public void tick(){
        if(Player.isMovingLeft()){
            velX = (int)Math.round(6 * -speed);
        }else if(Player.isMovingRight()){
            velX = (int)Math.round(6 * speed);
        }else{
            velX = 0;
        }
        x += velX;
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
            for(int i = 0; i < handler.maxX * 64; i += width){
                g2d.drawImage(texture, i + x, 0, null);
            }
    }
}
