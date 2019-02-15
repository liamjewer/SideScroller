package com.liam.scroller.framework;

import com.liam.scroller.window.Game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(mx >= GameMenu.play.getBounds().x && mx <= GameMenu.play.getBounds().x + GameMenu.play.getBounds().width){
            if (my >= GameMenu.play.getBounds().y && my <= GameMenu.play.getBounds().y + GameMenu.play.getBounds().height){
                Game.state = State.Game;
            }
        }

        if(mx >= GameMenu.exit.getBounds().x && mx <= GameMenu.exit.getBounds().x + GameMenu.exit.getBounds().width){
            if (my >= GameMenu.exit.getBounds().y && my <= GameMenu.exit.getBounds().y + GameMenu.exit.getBounds().height){
                System.exit(0);
            }
        }
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
}
