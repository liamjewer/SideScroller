package com.liam.scroller.framework;

import com.liam.scroller.window.Game;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseInput implements MouseInputListener {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(Game.state == State.Menu) {
            if (mouseOver(GameMenu.play.getBounds(), e)) {
                GameMenu.play.color = new Color(Math.max(0, GameMenu.play.Default.getRed() - 100), Math.max(0, GameMenu.play.Default.getGreen() - 100), Math.max(0, GameMenu.play.Default.getBlue() - 100));
            }else{
                GameMenu.play.color = GameMenu.play.Default;
            }
            if (mouseOver(GameMenu.exit.getBounds(), e)){
                GameMenu.exit.color = new Color(0,0,100);
            }else{
                GameMenu.exit.color = GameMenu.exit.Default;
            }
            if(mouseOver(GameMenu.controls.getBounds(), e)){
                GameMenu.controls.color = new Color(100, 0, 100);
            }else{
                GameMenu.controls.color = GameMenu.controls.Default;
            }
        }else if(Game.state == State.Controls){
            if(mouseOver(ControlsMenu.Return.getBounds(), e)){
                ControlsMenu.Return.color = new Color(0, 100, 100);
            }else{
                ControlsMenu.Return.color = ControlsMenu.Return.Default;
            }
        }else if(Game.state == State.Paused){
            if(mouseOver(PausedMenu.resume.getBounds(), e)){
                PausedMenu.resume.color = new Color(0, 100, 0);
            }else{
                PausedMenu.resume.color = PausedMenu.resume.Default;
            }
            if(mouseOver(PausedMenu.menu.getBounds(), e)){
                PausedMenu.menu.color = new Color(0,0,100);
            }else{
                PausedMenu.menu.color = PausedMenu.menu.Default;
            }
            if(mouseOver(PausedMenu.exit.getBounds(), e)){
                PausedMenu.exit.color = new Color(100, 0, 100);
            }else{
                PausedMenu.exit.color = PausedMenu.exit.Default;
            }
        }
    }
}
