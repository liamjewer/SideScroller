package com.liam.scroller.framework;

import com.liam.scroller.objects.Player;
import com.liam.scroller.window.Game;
import com.liam.scroller.window.ObjectHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    ObjectHandler handler;

    public KeyInput(ObjectHandler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        if(!Game.GameOver) {
            int k = e.getKeyCode();

            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Player) {
                    if(Game.state == State.Game) {
                        if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) {
                            tempObject.setVelX(5);
                            Player.lastFacing = true;
                        }
                        if (k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) {
                            tempObject.setVelX(-5);
                            Player.lastFacing = false;
                        }
                        if (k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
                            if (tempObject.getVelY() == 0) {
                                tempObject.setJumping(true);
                                tempObject.setVelY(-15);
                            }
                        }
                        if (k == KeyEvent.VK_ESCAPE) {
                            Game.state = State.Paused;
                        }
                    }else if(Game.state == State.Paused){
                        if (k == KeyEvent.VK_ESCAPE) {
                            Game.state = State.Game;
                        }
                    }
                }
            }
        }else{
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ObjectId.Player) {
                    tempObject.velY = 0;
                    tempObject.velX = 0;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int k = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectId.Player){
                if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
                if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) tempObject.setVelX(0);
            }
        }
    }
}
