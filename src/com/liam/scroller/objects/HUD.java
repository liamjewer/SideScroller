package com.liam.scroller.objects;

import com.liam.scroller.framework.Texture;
import com.liam.scroller.window.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class HUD {
    private static int count = 0;

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
        if(Arm.state == Arm.armState.pulled){
            int drawLocationX = Game.WIDTH - Texture.HUD.getHeight();
            int drawLocationY = 10;
            double rotationRequired = Math.toRadians(90);
            double locationX = Texture.HUD.getWidth() / 6.65;
            double locationY = Texture.HUD.getHeight() / 2.0;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(Texture.HUD, null), drawLocationX, drawLocationY, null);

            // TODO: 2019-02-25 add proper values with clip and reserve variables enum gun status (loaded, clip empty, no more reserve, no ammo)
            g.setColor(Color.GREEN);
            g.drawString("laser Pistol", Game.WIDTH - 65, 85);
            g.setFont(new Font("si", Font.PLAIN, 8));
            g.setColor(Color.BLACK);
            g.drawString("(Standard Issue)", Game.WIDTH - 62, 95);
            g.setColor(Color.GREEN);
            g.setFont(new Font("clip", Font.PLAIN, 30));
            g.drawString(Integer.toString(Arm.ammo), Game.WIDTH - 70, 130);
            g.setColor(Color.BLACK);
            g.setFont(new Font("/", Font.PLAIN, 50));
            g.drawString("/", Game.WIDTH - 35, 145);
            g.setColor(new Color(0, 150, 0));
            g.setFont(new Font("reserve", Font.PLAIN, 15));
            g.drawString(Integer.toString(Arm.reserve), Game.WIDTH - 20, 140);
            if(Arm.status == Arm.Status.loaded){
                g.setColor(Color.GREEN);
            }else{
                if(count > 50 && count < 100) {
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.BLACK);
                }
                if(count == 100) count = 0;
            }

            g.setFont(new Font("status", Font.PLAIN, 15));
            g.drawString("status:", Game.WIDTH - 55, 170);
            g.setFont(new Font("status", Font.BOLD, 10));
            g.drawString(Arm.status.toString(), Game.WIDTH - 55, 190);


        }
    }
}
