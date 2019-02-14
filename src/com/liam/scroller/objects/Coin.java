package com.liam.scroller.objects;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.framework.Texture;
import com.liam.scroller.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Coin extends GameObject {
    Texture texture = Game.getInstance();
    public Coin(float x, float y, ObjectId id) {
        super(x, y, id);
        this.id = id;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.coin, (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32,32);
    }
}
