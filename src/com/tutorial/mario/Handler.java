package com.tutorial.mario;

import java.awt.Graphics;
import java.util.LinkedList;

import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.tile.Tile;
import com.tutorial.mario.tile.Wall;

public class Handler  {
	
	public LinkedList<Entity> entitys = new LinkedList<Entity>();
	public LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	public Handler()
	{
		creatLevel();
	}
	
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
	
	public void creatLevel()
	{
		for(int i=0; i<=Game.WIDTH*Game.scale/64;i++)
		{
			addTile(new Wall(i*64,Game.HEIGHT*Game.scale-64,64,64,true,Id.wall,this));
			if(i!=0&&i!=1&&i!=15&&i!=16&&i!=17)
			{
				addTile(new Wall(i*64,300,64,64,true,Id.wall,this));
			}
		}
	}


	

}
