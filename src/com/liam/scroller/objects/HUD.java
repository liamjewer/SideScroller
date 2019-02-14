package com.liam.scroller.objects;

import com.liam.scroller.framework.Texture;
import com.liam.scroller.window.Game;

import java.awt.*;

public class HUD {
    public static void render(Graphics g){
        g.drawImage(Texture.HUD, Game.WIDTH/2 - 125, 0, null);
        g.setColor(Color.WHITE);
        g.drawString("Level: " + Game.LEVEL, Game.WIDTH/2 - 100, 15);
        g.drawImage(Texture.coin, Game.WIDTH/2 + 70, 3, 15, 15, null);
        g.drawString(": " + Player.Points,Game.WIDTH/2 + 90, 15);
        g.setColor(Color.RED);
        g.fillRect(Game.WIDTH/2 - 100, 25, Player.health * 2, 20);
        g.setColor(Color.white);
        g.drawRect(Game.WIDTH/2 - 100, 25, 200, 20);
        for(int i =0; i < Player.lives; i++){
            g.drawImage(Texture.lives, i * 21 + Game.WIDTH/2 - 50,50, null);
        }
    }
}
