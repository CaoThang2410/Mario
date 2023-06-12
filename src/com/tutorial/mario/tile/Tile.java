package com.tutorial.mario.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public abstract class Tile {
	public int x,y;
	public int width,height;
	
	public boolean solid;
	
	public int velX, velY;
	
	public Handler handler;
	public Id id;
	public Tile(int x, int y, int width, int height, boolean solid,Id id, Handler handler)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.handler = handler;
		this.id= id;
	}
	
	public void die()
	{
		handler.removeTile(this);
	}
	public abstract void render(Graphics g);
	public Id getId()
	{
		return id;
	}
	
	public abstract void tick();

	
	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public Rectangle getBounds()
	{
		return new Rectangle(getX(),getY(),width,height);
	}


	public boolean isSolid() {
		return solid;
	}

}
