package com.liam.scroller.objects;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.framework.Texture;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.LinkedList;

public class Arm extends GameObject {
    public static float angle;
    public static final int clip = 5;
    public static int ammo = clip;
    public static int reserve = clip * 3;

    public enum armState{
        holstered,
        pulled
    }
    public enum Status{
        loaded, empty_clip, no_reserve, no_ammo
    }
    public static Status status = Status.loaded;
    public static armState state = armState.holstered;

    Arm(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if(ammo == 0 && reserve == 0) status = Status.no_ammo;
        else if(reserve == 0) status = Status.no_reserve;
        else if(ammo == 0) status = Status.empty_clip;
        else if(ammo > 0 && reserve > 0) status = Status.loaded;
    }

    @Override
    public void render(Graphics g) {
        if(state == armState.pulled) {
            int drawLocationX = (int) x;
            int drawLocationY = (int) y;
            double rotationRequired = angle;
            double locationX = Texture.arm.getWidth() / 2;
            double locationY = Texture.arm.getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(Texture.arm, null), drawLocationX, drawLocationY, null);
        }
    }

    public static void reload(){
        if(reserve - (clip - ammo) < 0){
            ammo += reserve;
            reserve = 0;
        }else {
            reserve -= clip - ammo;
            ammo = clip;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(Texture.arm.getWidth(), Texture.arm.getHeight());
    }
}
