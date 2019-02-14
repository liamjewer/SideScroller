package com.liam.scroller.objects;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.framework.Texture;
import com.liam.scroller.window.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.LinkedList;

public class Portal extends GameObject {
    Texture texture = Game.getInstance();
    private double angle = 0.0;
    public Portal(float x, float y, ObjectId id) {
        super(x, y, id);
        this.id = id;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        double rotationRequired = Math.toRadians (angle);
        double locationX = Texture.Portal.getWidth() / 2;
        double locationY = Texture.Portal.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        // Drawing the rotated image at the required drawing locations
        g2d.drawImage(op.filter(Texture.Portal, null), (int) x, (int) y, null);
        angle += 0.2;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 64,64);
    }
}
