package com.liam.scroller.objects;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.framework.Texture;
import com.liam.scroller.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {
    Texture texture = Game.getInstance();
    private int type;
    public Block(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.id = id;
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.block[type], (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32,32);
    }
}
