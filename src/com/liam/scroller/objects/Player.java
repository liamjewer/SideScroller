package com.liam.scroller.objects;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.MouseInput;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.framework.Texture;
import com.liam.scroller.window.Animation;
import com.liam.scroller.window.Camera;
import com.liam.scroller.window.Game;
import com.liam.scroller.window.ObjectHandler;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {
    private final Camera cam;
    public static float width = 32;
    public float height = 64;
    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;
    private ObjectHandler handler;
    Texture texture = Game.getInstance();
    boolean showBounds = false;
    public static boolean lastFacing = true; //true is walking right false is walking left

    private Animation playerWalkRight;
    private Animation playerWalkLeft;
    private Animation playerJumpRight;
    private Animation playerJumpLeft;
    public static int health = 100;
    public static int lives = 5;
    public static int Points = 0;
    private static boolean ismoving;
    public int lastX = 0;
    private Arm arm;
    private Laser laser;
    public static float dX;
    public static float dY;
    public float hyp;
    public static float scaleF;

    public Player(float x, float y, ObjectHandler handler, ObjectId id, Camera cam) {
        super(x, y, id);
        this.id = id;
        this.handler = handler;
        this.cam = cam;
        health = 100;
        lastFacing = true;
        arm = new Arm(x - 7, y - 2, ObjectId.Arm);
        handler.addObject(arm);

        playerWalkRight = new Animation(4, texture.player[0], texture.player[1], texture.player[2], texture.player[3]);
        playerWalkLeft = new Animation(4, texture.player[7], texture.player[6], texture.player[5], texture.player[4]);
        playerJumpRight = new Animation(10, texture.playerJump[0], texture.playerJump[1], texture.playerJump[2], texture.playerJump[3]);
        playerJumpLeft = new Animation(10, texture.playerJump[7], texture.playerJump[6], texture.playerJump[5], texture.playerJump[4]);

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        y += velY;
        x += velX;
        arm.setX(x - 7);
        arm.setY(y - 2);

        if(x != lastX){
            ismoving = true;
        }else{
            ismoving = false;
        }
        lastX = (int) x;

        if(falling || jumping){
            velY += gravity;
            if(velY > MAX_SPEED)
                velY = MAX_SPEED;
        }
        if(y > Game.HEIGHT){
            handler.respawn();
        }
        if(health <= 0){
            handler.respawn();
        }

        dX = MouseInput.mx - x;
        dY = MouseInput.my - y;
        if(dX < 0){
            Arm.angle = (float) Math.atan(dY/dX) + (float) Math.toRadians(180);
        }else{
            Arm.angle = (float) Math.atan(dY / dX);
        }
        hyp = (float) Math.sqrt(dX*dX + dY*dY);
        scaleF = hyp/23;
        Laser.originX = Math.round(dX/scaleF) + arm.getX() + Texture.arm.getWidth()/2;
        Laser.originY = Math.round(dY/scaleF) + arm.getY() + Texture.arm.getHeight()/2;

        Collision(object);
        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
        //playerJumpRight.runAnimation();
        //playerJumpLeft.runAnimation();
    }

    private void Collision(LinkedList<GameObject> object){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Block){
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY() + height;
                    velY = 0;
                }
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }else{
                    falling = true;
                }
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - width;
                    ismoving = false;
                }
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    x = tempObject.getX() + width;
                    ismoving = false;
                }
            }else if(tempObject.getId() == ObjectId.Portal){
                //switch levels
                if(getBounds().intersects(tempObject.getBounds())) {
                    handler.switchLevel();
                }
            }else if(tempObject.getId() == ObjectId.Lava){
                if(getBounds().intersects(tempObject.getBounds())) {
                    health -= 1;
                    velY = 1;
                    falling = false;
                    jumping = false;
                }
            }else if(tempObject.getId() == ObjectId.Spikes){
                if(getBounds().intersects(tempObject.getBounds())) {
                    handler.getObjectById(ObjectId.Player).setVelX(0);
                    handler.getObjectById(ObjectId.Player).setVelY(0);
                    handler.respawn();
                }
            }else if(tempObject.getId() == ObjectId.Coin){
                if(getBounds().intersects(tempObject.getBounds())){
                    Points++;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(jumping) {
            if (lastFacing)
                g.drawImage(texture.playerJump[3], (int) x, (int) y, null);
            else
                g.drawImage(texture.playerJump[4], (int) x, (int) y, null);
        }else {
            if (velX > 0) {
                playerWalkRight.drawAnimation(g, (int) x, (int) y);
                lastFacing = true;
            } else if (velX < 0) {
                playerWalkLeft.drawAnimation(g, (int) x, (int) y);
                lastFacing = false;
            } else {
                if (lastFacing)
                    g.drawImage(texture.player[0], (int) x, (int) y, null);
                else
                    g.drawImage(texture.player[7], (int) x, (int) y, null);
            }
        }

        Graphics2D g2d = (Graphics2D) g;
        if (showBounds) {
            g.setColor(Color.red);
            g2d.draw(getBounds());
            g2d.draw(getBoundsTop());
            g2d.draw(getBoundsRight());
            g2d.draw(getBoundsLeft());
        }
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x+(width/2)-(width/4)), (int) ((int)y + height/2), (int)width/2, (int)height/2 + 1);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+(width/2)-(width/4)), (int)y, (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x + width - (width/4)), (int)y + 5, (int)width/4, (int)height - 10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, (int)width/4, (int)height - 10);
    }

    public static boolean isMovingLeft(){
        return velX > 0;
    }

    public static boolean isMovingRight(){
        return velX < 0;
    }

    public static boolean isMoving() {
        return ismoving;
    }
}
