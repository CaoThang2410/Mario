package com.tutorial.mario.powerup;

import java.awt.Graphics;
import java.util.Random;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.tile.Tile;

public class Mushroom extends Entity{
	
	private Random random = new Random();

	public Mushroom(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
		int dir = random.nextInt(2);
		switch(dir)
		{
		case 0:
			setVelX(-1);
			break;
		case 1:
			setVelX(1);
			break;
		}
	}
	public void render(Graphics g)
	{
		g.drawImage(Game.mushroom.getBufferedImage(), x, y, width, height, null);
	}
	
	public void tick()
	{
		x+=velX;
		y+=velY;
		
		for (Tile t : handler.tiles) {
			if (!t.solid)
				break;
			if (t.getId() == Id.wall) {
			
				if (getBoundsBottom().intersects(t.getBounds())) {
					setVelY(0);
					y = t.getY() - t.height;
					if (falling) {
						falling = false;
					}

				} else {
					if (!falling) {
						gravity = 0.0;
						falling = true;
					}
				}

				if (getBoundsLeft().intersects(t.getBounds())) {
					setVelX(3);
				}
				if (getBoundsRight().intersects(t.getBounds())) {
					setVelX(-3);
				}
			}
		}
		if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}
	}
	

}
