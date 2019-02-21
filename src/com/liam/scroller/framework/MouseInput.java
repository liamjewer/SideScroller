package com.liam.scroller.framework;

import com.liam.scroller.objects.Arm;
import com.liam.scroller.objects.Player;
import com.liam.scroller.window.Game;

import javax.print.attribute.standard.JobKOctets;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseInput implements MouseInputListener {
    private float dX, dY;

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
        int mx = e.getX();
        int my = e.getY();

        if(Game.state == State.Menu) {
            if (mouseOver(GameMenu.play.getBounds(), e)) {
                GameMenu.play.color = GameMenu.play.Default.darker();
            }else{
                GameMenu.play.color = GameMenu.play.Default;
            }
            if (mouseOver(GameMenu.exit.getBounds(), e)){
                GameMenu.exit.color = GameMenu.exit.Default.darker();
            }else{
                GameMenu.exit.color = GameMenu.exit.Default;
            }
            if(mouseOver(GameMenu.controls.getBounds(), e)){
                GameMenu.controls.color = GameMenu.controls.Default.darker();
            }else{
                GameMenu.controls.color = GameMenu.controls.Default;
            }
        }else if(Game.state == State.Controls){
            if(mouseOver(ControlsMenu.Return.getBounds(), e)){
                ControlsMenu.Return.color = ControlsMenu.Return.Default.darker();
            }else{
                ControlsMenu.Return.color = ControlsMenu.Return.Default;
            }
        }else if(Game.state == State.Paused){
            if(mouseOver(PausedMenu.resume.getBounds(), e)){
                PausedMenu.resume.color = PausedMenu.resume.Default.darker();
            }else{
                PausedMenu.resume.color = PausedMenu.resume.Default;
            }
            if(mouseOver(PausedMenu.menu.getBounds(), e)){
                PausedMenu.menu.color = PausedMenu.menu.Default.darker();
            }else{
                PausedMenu.menu.color = PausedMenu.menu.Default;
            }
            if(mouseOver(PausedMenu.exit.getBounds(), e)){
                PausedMenu.exit.color = PausedMenu.exit.Default.darker();
            }else{
                PausedMenu.exit.color = PausedMenu.exit.Default;
            }
        }else if(Game.state == State.Game){
            dX = mx - Game.handler.getObjectById(ObjectId.Player).getX();
            dY = my - Game.handler.getObjectById(ObjectId.Player).getY();

            Arm.angle = (float) Math.atan(dY/dX);
        }
    }
}
