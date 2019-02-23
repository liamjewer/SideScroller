package com.liam.scroller.objects;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Laser extends GameObject {
    public static float originX, originY;
    public Laser(float x, float y, ObjectId id) {
        super(x, y, id);
        x = originX;
        y = originY;
        velX = Math.round(Player.dX/Player.scaleF);
        velY = Math.round(Player.dY/Player.scaleF);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval((int)x, (int)y, 10,10);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
