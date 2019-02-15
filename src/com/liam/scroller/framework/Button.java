package com.liam.scroller.framework;

import java.awt.*;

public class Button {
    private final ButtonID id;
    private int width, height;
    private float x, y;
    private boolean filled;
    Color color;
    String string;
    public Button(float x, float y, int width, int height, boolean filled, String string, Color color, ButtonID id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filled = filled;
        this.id = id;
        this.color = color;
        this.string = string;
    }
    public void render(Graphics g){
        g.setColor(color);
        if(filled){
            g.fillRect((int) x, (int) y, width, height);
        }else{
            g.drawRect((int) x, (int) y, width, height);
        }
    }
}
