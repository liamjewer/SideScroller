package com.liam.scroller.framework;

import com.liam.scroller.window.Game;

import java.awt.*;

public class ControlsMenu {
    static Button Return;

    public ControlsMenu(){
        Return = new Button(250, 450, 300, 100, true, "Main Menu", Color.CYAN, Color.WHITE, ButtonID.Menu);
    }

    public void render(Graphics g){
        g.setColor(new Color(0,0,0, 175));
        g.fillRect(60, 40, Game.WIDTH - 120, Game.HEIGHT - 80);
        g.setColor(Color.WHITE);
        g.setFont(new Font("controls", Font.BOLD, 50));
        g.drawString("W/Up - Jump", Game.WIDTH/2 - g.getFontMetrics().stringWidth("W/Up - Jump")/2, 100);
        g.drawString("A/Left - Walk Left", Game.WIDTH/2 - g.getFontMetrics().stringWidth("A/Left - Walk Left")/2, 200);
        g.drawString("D/Right - Walk Right", Game.WIDTH/2 - g.getFontMetrics().stringWidth("D/Right - Walk Right")/2, 300);
        g.drawString("Escape - Pause", Game.WIDTH/2 - g.getFontMetrics().stringWidth("Escape - Pause")/2, 400);
        Return.render((Graphics2D) g);
    }
}
