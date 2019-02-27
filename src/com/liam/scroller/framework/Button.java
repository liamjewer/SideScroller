package com.liam.scroller.framework;

import java.awt.*;
import java.awt.event.MouseListener;

public class Button {
    private final ButtonID id;
    private int width, height;
    protected float x, y;
    private boolean filled;
    Color color, textColor, Default;
    String string;
    public Button(float x, float y, int width, int height, boolean filled, String string, Color color, Color textColor, ButtonID id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filled = filled;
        this.id = id;
        this.color = color;
        this.Default = color;
        this.textColor = textColor;
        this.string = string;
    }

    public void render(Graphics2D g){
        g.setColor(color);
        g.setStroke(new BasicStroke(5));
        if(filled){
            g.fillRect((int) x, (int) y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect((int) x, (int) y, width, height);
        }else{
            g.drawRect((int) x, (int) y, width, height);
        }
        g.drawImage(Texture.button, (int) x, (int) y, width, height, null);
        g.setColor(textColor);
        g.setFont(new Font("button", Font.BOLD,height/4));
        g.drawString(string, (int)((width/2 - g.getFontMetrics().stringWidth(string)/2) + x), (int)(height/2 + 15 + y));
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, width, height);
    }
}
