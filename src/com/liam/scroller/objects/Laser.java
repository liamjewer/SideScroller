package com.liam.scroller.objects;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.framework.Texture;
import com.liam.scroller.window.Animation;
import com.liam.scroller.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Laser extends GameObject {
    private float x, y;
    private float velX, VelY;
    int life;
    private Animation explosion;

    public static float originX, originY;
    private boolean collided = false;

    public Laser(float x, float y, int life, ObjectId id) {
        super(x, y, id);
        this.x = originX;
        this.y = originY;
        this.life = life;
        this.velX = Math.round((Player.dX/Player.scaleF));
        this.velY = Math.round((Player.dY/Player.scaleF));
        explosion = new Animation(1, Texture.explosion[0], Texture.explosion[5], Texture.explosion[10], Texture.explosion[11]);
        Arm.ammo --;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;
        if(life <= 0){
            Game.handler.removeObject(this);
        }else{
            life --;
        }

        for(int i = 0; i < Game.handler.object.size(); i++){
            GameObject tempObject = Game.handler.object.get(i);

            if(tempObject.getId() == ObjectId.Block){
                if(laserOver(tempObject.getBounds())){
                    velX = 0;
                    velY = 0;
                    explosion.runAnimation();
                    collided = true;
                }
            }else if(tempObject.getId() == ObjectId.Lava){
                if(laserOver(tempObject.getBounds())){
                    velX = 0;
                    velY = 0;
                    explosion.runAnimation();
                    collided = true;
                }
            }else if(tempObject.getId() == ObjectId.Spikes){
                if(laserOver(tempObject.getBounds())){
                    velX = 0;
                    velY = 0;
                    explosion.runAnimation();
                    collided = true;
                }
            }
        }
    }

    private boolean laserOver(Rectangle rect){

        if(x >= rect.x && x <= rect.x + rect.width){
            if (y >= rect.y && y <= rect.y + rect.height){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        if(collided) {
            explosion.drawAnimation(g, (int)x - 16, (int)y - 16);
        }else {
            g.setColor(Color.GREEN);
            g2d.setStroke(new BasicStroke(3));
            g.drawLine((int) x, (int) y, (int) (x + velX), (int) (y + velY));
            g2d.setStroke(new BasicStroke(1));
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
