package com.liam.scroller.window;

import com.liam.scroller.framework.GameObject;
import com.liam.scroller.framework.ObjectId;
import com.liam.scroller.framework.State;
import com.liam.scroller.framework.Texture;
import com.liam.scroller.objects.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ObjectHandler {
    private final BufferedImageLoader loader = new BufferedImageLoader();
    public LinkedList<GameObject> object = new LinkedList<>();

    private GameObject tempObject;
    public static int maxX = 0;
    private Camera cam;
    private ParallaxEngine paralaxEngine;
    private BufferedImage[] levels = new BufferedImage[4];
    public ParallaxLayer mid;
    public ParallaxLayer back;
    public ParallaxLayer front;
    private boolean startup = true;


    public ObjectHandler(Camera cam){
        this.cam = cam;

        levels[0] = loader.loadImage("/level1.png"); //loading the level
        levels[1] = loader.loadImage("/level2.png"); //loading the level
        levels[2] = loader.loadImage("/level3.png"); //loading the level
        levels[3] = loader.loadImage("/level4.png"); //loading the level
        mid = new ParallaxLayer(Texture.background[0], 0.5, 0, this); //clouds
        back = new ParallaxLayer(Texture.background[1], 0.3, 0, this); //mountains
        front = new ParallaxLayer(Texture.background[2], 0.7, 0, this); //hills
        this.paralaxEngine = new ParallaxEngine(back, mid, front);
    }

    public void tick(){
        paralaxEngine.tick();
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);

            tempObject.tick(object);
        }
        if(startup && Game.state == State.Game){
            startup = false;
            loadImageLevel(levels[Game.LEVEL - 1]);
        }
    }

    public void render(Graphics g){
        paralaxEngine.render((Graphics2D) g);
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        clearLevel();
        // TODO: 2019-02-25 add random spawns for ammo packs, health packs etc. 
        for(int xx = 0; xx < w; xx++){
            for(int yy = 0; yy < h; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //portal
                if(red == 255 && green == 0 && blue == 255){
                    addObject(new Portal(xx*32, yy*32, ObjectId.Portal));
                }
                //blocks
                if(red == 255 && green == 255 && blue == 255){
                    addObject(new Block(xx*32, yy*32, 1, ObjectId.Block));
                    if(xx > maxX) maxX = xx;
                }
                if(red == 255 && green == 255 && blue == 0){
                    addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));
                    if(xx > maxX) maxX = xx;
                }
                if(red == 200 && green == 200 && blue == 200){
                    addObject(new Block(xx*32, yy*32, 2, ObjectId.Block));
                    if(xx > maxX) maxX = xx;
                }
                if (red == 0 && blue == 255 && green == 0){
                    addObject(new Block(xx*32, yy*32, 1, ObjectId.Null_Block));
                }
                //danger Blocks
                if(red == 255 && green == 0 && blue == 0){
                    addObject(new DangerBlock(xx*32, yy*32, 0, ObjectId.Lava));
                }
                if(red == 48 && green == 48 && blue == 48){
                    addObject(new DangerBlock(xx*32, yy*32, 1, ObjectId.Spikes));
                }
                //coins
                if(red == 255 && green == 150 && blue == 0){
                    addObject(new Coin(xx*32, yy*32, ObjectId.Coin));
                }
                //player
                if(red == 0 && green == 255 && blue == 0){
                    addObject(new Player(xx*32, yy*32, this, ObjectId.Player, cam));
                }
            }
        }
    }

    public void switchLevel() {
        Game.LEVEL++;
        getObjectById(ObjectId.Player).setVelX(0);
        Player.pointsThisLevel = 0;
        if(Game.LEVEL <= levels.length) {
            loadImageLevel(levels[Game.LEVEL - 1]);
        }else{
            Game.state = State.Winner;
        }
    }

    public void respawn(){
        if(Player.lives > 0){
            clearLevel();
            loadImageLevel(levels[Game.LEVEL - 1]);
            Player.lives--;
            Player.Points -= Player.pointsThisLevel;
            Player.pointsThisLevel = 0;
        }else{
            Game.state = State.GameOver;
        }
    }

    public GameObject getObjectById(ObjectId id){
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == id) {
                return tempObject;
            }
        }
        return null;
    }

    private void clearLevel(){
        object.clear();
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
