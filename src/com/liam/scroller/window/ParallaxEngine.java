package com.liam.scroller.window;

import java.awt.*;

public class ParallaxEngine {
    private ParallaxLayer[] layers;

    public ParallaxEngine(ParallaxLayer... layers){
        this.layers = layers;
    }

    public void tick(){
        for(int i = 0; i < layers.length; i++)
            layers[i].tick();
    }

    public void render(Graphics2D g2d){
        for(int i = 0; i < layers.length; i++)
            layers[i].render(g2d);
    }
}
