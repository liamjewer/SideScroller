package com.liam.scroller.framework;

import com.liam.scroller.window.Game;
import com.liam.scroller.window.Window;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Game.state == State.Menu) {
            if (mouseOver(GameMenu.play.getBounds(), e)) {
                Game.state = State.Game;
            } else if (mouseOver(GameMenu.exit.getBounds(), e)){
                System.exit(0);
            }else if(mouseOver(GameMenu.controls.getBounds(), e)){
                Game.state = State.Controls;
            }
        }else if(Game.state == State.Controls){
            if(mouseOver(ControlsMenu.Return.getBounds(), e)){
                Game.state = State.Menu;
            }
        }else if(Game.state == State.Paused){
            if(mouseOver(PausedMenu.resume.getBounds(), e)){
                Game.state = State.Game;
            }else if(mouseOver(PausedMenu.menu.getBounds(), e)){
                Game.state = State.Menu;
            }else if(mouseOver(PausedMenu.exit.getBounds(), e)){
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private boolean mouseOver(Rectangle rect, MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(mx >= rect.x && mx <= rect.x + rect.width){
            if (my >= rect.y && my <= rect.y + rect.height){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}