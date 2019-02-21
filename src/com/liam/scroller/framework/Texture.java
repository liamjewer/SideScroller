package com.liam.scroller.framework;

import com.liam.scroller.window.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {
    public static BufferedImage HUD;
    SpriteSheet bs, ps;
    private BufferedImage block_sheet = null;
    BufferedImage player_sheet = null;
    public static BufferedImage Portal = null;
    public static BufferedImage lives = null;
    public static BufferedImage coin = null;
    public static BufferedImage button = null;
    public static BufferedImage arm;

    public BufferedImage[] block = new BufferedImage[3];
    public BufferedImage[] player = new BufferedImage[8];
    public BufferedImage[] playerJump = new BufferedImage[8];
    public BufferedImage[] dangerBlock = new BufferedImage[2];
    public static BufferedImage[] background = new BufferedImage[3];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        block_sheet = loader.loadImage("/block_sheet.png");
        player_sheet = loader.loadImage("/player_sheet.png");
        Portal = loader.loadImage("/Portal.png");
        lives = loader.loadImage("/Lives.png");
        button = loader.loadImage("/Button.png");
        arm = loader.loadImage("/arm.png");

        background[0] = loader.loadImage("/clouds.png");
        background[1] = loader.loadImage("/mountains.png");
        background[2] = loader.loadImage("/Hills.png");

        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        HUD = loader.loadImage("/HUD.png");
        
        getTextures();
    }

    private void getTextures() {
        block[0] = bs.grabImage(1,1, 32, 32); //grass block
        block[1] = bs.grabImage(2,1, 32, 32); //dirt block
        block[2] = bs.grabImage(3,1, 32, 32); //metal
        player[0] = ps.grabImage(1,1, 32,64); //player standing right
        player[1] = ps.grabImage(2,1, 32,64); //player walking right 1
        player[2] = ps.grabImage(3,1, 32,64); //player walking right 2
        player[3] = ps.grabImage(4,1, 32,64); //player walking right 3
        player[4] = ps.grabImage(5,1, 32,64); //player walking left 3
        player[5] = ps.grabImage(6,1, 32,64); //player walking left 2
        player[6] = ps.grabImage(7,1, 32,64); //player walking left 1
        player[7] = ps.grabImage(8,1, 32,64); //player standing left
        playerJump[0] = ps.grabImage(1,2, 32,64); //player Jumping right
        playerJump[1] = ps.grabImage(2,2, 32,64); //player Jumping right 1
        playerJump[2] = ps.grabImage(3,2, 32,64); //player Jumping right 2
        playerJump[3] = ps.grabImage(4,2, 32,64); //player Jumping right 3
        playerJump[4] = ps.grabImage(5,2, 32,64); //player Jumping left 3
        playerJump[5] = ps.grabImage(6,2, 32,64); //player Jumping left 2
        playerJump[6] = ps.grabImage(7,2, 32,64); //player Jumping left 1
        playerJump[7] = ps.grabImage(8,2, 32,64); //player Jumping left
        dangerBlock[0] = bs.grabImage(1,2, 32, 32); //lava
        dangerBlock[1] = bs.grabImage(2,2, 32, 32); //spikes
        coin = bs.grabImage(1,3, 32, 32); //coin
    }
}
