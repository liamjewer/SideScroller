package com.liam.scroller.framework;

import com.liam.scroller.window.Game;

import java.awt.*;

public class ControlsMenu {
    static Button Return;

    public ControlsMenu(){
        Return = new Button(250, 450, 300, 100, true, "Main Menu", Color.GREEN, Color.WHITE, ButtonID.Menu);
    }

    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("controls", Font.BOLD, 50));
        g.drawString("W/Up - Jump", Game.WIDTH/2 - g.getFontMetrics().stringWidth("W/Up - Jump")/2, 50);
        g.drawString("A/Left - Walk Left", Game.WIDTH/2 - g.getFontMetrics().stringWidth("A/Left - Walk Left")/2, 150);
        g.drawString("D/Right - Walk Right", Game.WIDTH/2 - g.getFontMetrics().stringWidth("D/Right - Walk Right")/2, 250);
        g.drawString("Escape - Pause", Game.WIDTH/2 - g.getFontMetrics().stringWidth("Escape - Pause")/2, 350);
        Return.render((Graphics2D) g);
    }
}
