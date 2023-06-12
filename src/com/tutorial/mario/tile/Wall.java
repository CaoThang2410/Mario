package com.tutorial.mario.tile;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public class Wall extends Tile {

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.grass.getBufferedImage(),x,y,width,height,null);
	}

	
	public void tick() {
		
	}

}
