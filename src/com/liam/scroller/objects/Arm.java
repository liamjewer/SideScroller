package com.liam.scroller.objects;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.framework.Texture;


import java.awt.*;
import java.util.LinkedList;

public class Arm extends GameObject {
    public static float angle;

    Arm(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Texture.arm, (int) x,(int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(Texture.arm.getWidth(), Texture.arm.getHeight());
    }
}
