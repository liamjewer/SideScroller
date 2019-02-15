package com.liam.scroller.window;


import com.liam.scroller.framework.GameObject;

public class Camera {
    private float x, y;
    public static boolean isMoving;

    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void tick(GameObject player) {
        if (player.getX() >= Game.WIDTH/2 && player.getX() <= ObjectHandler.maxX * 32 - Game.WIDTH/2 + 32) {
            x = -player.getX() + Game.WIDTH / 2;
            isMoving = true;
        }else if(player.getX() >= ObjectHandler.maxX * 32 - Game.WIDTH/2 + 32){
            x = -ObjectHandler.maxX * 32 + Game.WIDTH - 32;
            isMoving = false;
        }else{
            x = 0;
            y = 0;
            isMoving = false;
        }
    }
}
