package com.liam.scroller.window;

import com.liam.scroller.objects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ParallaxLayer {
    private BufferedImage texture;
    private int width;
    private double speed;
    private int gap;
    private double velX;
    private ArrayList<Double> x = new ArrayList<>();
    private ObjectHandler handler;

    public ParallaxLayer(BufferedImage texture, double speed, int gap, ObjectHandler handler){
        this.texture = texture;
        this.gap = gap;
        this.width = texture.getWidth();
        this.speed = speed;
        this.handler = handler;

        for(double i = 0; i < ObjectHandler.maxX * 32 + width; i += width){
            x.add(i);
        }
    }

    public void tick(){
        if(Camera.isMoving && Player.isMoving()) {
            if (Player.isMovingLeft()) {
                velX = -speed;
            } else if (Player.isMovingRight()) {
                velX = speed;
            } else {
                velX = 0;
            }
        }else {
            velX = 0;
        }
        for(int i = 0; i < x.size(); i ++){
            double temp = x.get(i);
            temp += velX;
            x.set(i, temp);
        }
        for(int i = 0; i < x.size(); i ++){
            if(x.get(i) <= -Game.WIDTH){
                x.set(i, (double)ObjectHandler.maxX * 32);
            }
        }
    }

    public void render(Graphics2D g2d){
            for (int i = 0; i < x.size(); i ++) {
                g2d.drawImage(texture, (int) Math.round(x.get(i)), 0, null);
            }
    }
}
