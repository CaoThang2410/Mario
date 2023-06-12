package com.tutorial.mario;

import java.awt.Graphics;
import java.util.LinkedList;

import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.tile.Tile;

public class Handler  {
	
	public LinkedList<Entity> entitys = new LinkedList<Entity>();
	public LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	public void render(Graphics g)
	{
		for(Entity en: entitys)
		{
			en.render(g);
		}
		for(Tile ti: tiles)
		{
			ti.render(g);
		}
	}
	
	public void tick()
	{
		for(Entity en: entitys)
		{
			en.tick();;
		}
		for(Tile ti: tiles)
		{
			ti.tick();;
		}
	}
	
	public void addEntity(Entity en)
	{
		entitys.add(en);
	}
	
	public void removeEntity(Entity en)
	{
		entitys.remove(en);
	}
	
	public void addTile(Tile ti)
	{
		tiles.add(ti);
	}
	
	public void removeTile(Tile ti)
	{
		tiles.remove(ti);
	}


	

}
