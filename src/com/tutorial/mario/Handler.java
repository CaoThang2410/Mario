package com.tutorial.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.powerup.Mushroom;
import com.tutorial.mario.tile.Tile;
import com.tutorial.mario.tile.Wall;

public class Handler {

	public LinkedList<Entity> entitys = new LinkedList<Entity>();
	public LinkedList<Tile> tiles = new LinkedList<Tile>();

	public Handler() {

	}

	public void render(Graphics g) {
		for (Entity en : entitys) {
			en.render(g);
		}
		for (Tile ti : tiles) {
			ti.render(g);
		}
	}

	public void tick() {
		for (int i = 0; i < entitys.size(); i++) {
			entitys.get(i).tick();
		}

		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).tick();
		}

	}

	public void addEntity(Entity en) {
		entitys.add(en);
	}

	public void removeEntity(Entity en) {
		entitys.remove(en);
	}

	public void addTile(Tile ti) {
		tiles.add(ti);
	}

	public void removeTile(Tile ti) {
		tiles.remove(ti);
	}

	public void createLevel(BufferedImage level) {
//		for(int i=0; i<=Game.WIDTH*Game.scale/64;i++)
//		{
//			addTile(new Wall(i*64,Game.HEIGHT*Game.scale-64,64,64,true,Id.wall,this));
//			if(i!=0&&i!=1&&i!=15&&i!=16&&i!=17)
//			{
//				addTile(new Wall(i*64,300,64,64,true,Id.wall,this));
//			}
//		}
		int width = level.getWidth();
		int height = level.getHeight();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = level.getRGB(x, y);

				// pixel>>16 : dời pixel qua phải 16bit : dời 0100 qua 1 : 0010
				// &0xff : thực hiện and với 255 để đảm bảo red nằm trong khoảng 0-255
				int red = (pixel >> 16) & 0xFF;
				int green = (pixel >> 8) & 0xFF;
				int blue = (pixel) & 0xFF;
				if (red == 0 && green == 0 && blue == 0)// nếu màu đen thì thêm bức tường
				{
					addTile(new Wall(x * 64, y * 64, 64, 64, true, Id.wall, this));
				}
				if (red == 0 && green == 0 && blue == 255) {//màu xanh thì hiện nv
					addEntity(new Player(x * 64, y * 64, 64, 64, false, Id.player, this));
				}
				if (red == 255 && green == 0 && blue == 0) {//màu đỏ thì xuất hiện nước tăng lực
					addEntity(new Mushroom(x * 64, y * 64, 64, 64, true, Id.mushroom, this));
				}
			}
		}

	}

}
