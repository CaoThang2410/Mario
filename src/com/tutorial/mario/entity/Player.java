package com.tutorial.mario.entity;

import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.tile.Tile;

public class Player extends Entity {

	private int frame = 0;

	private int frameDelay = 0;

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	@Override
	public void render(Graphics g) {

		if (facing == 0) {
			g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);
		} else if (facing == 1) {
			g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);

		}

		// g.drawImage(Game.player[2].getBufferedImage(),x,y,width,height,null);

	}

	@Override
	public void tick() {
		x += getVelX();
		y += getVelY();
		if (x <= 0)
			x = 0;
		if (y <= 0)
			y = 0;
		// xóa giới hạn để nhân vật có thể di chuyển trong map đc
//		if (x + width >= 1080)
//			x = 1080 - width;
//		if (y + height >= 771)
//			y = 771 - height;
		for (Tile t : handler.tiles) {
			if (!t.solid)
				break;
			if (t.getId() == Id.wall) {
				if (getBoundsTop().intersects(t.getBounds())) {
					setVelY(0);
					// y= t.getY()+t.height;
					if (jumping) {
						jumping = false;
						gravity = 0.0;
						falling = true;
					}

				}
				if (getBoundsBottom().intersects(t.getBounds())) {
					setVelY(0);
					y = t.getY() - t.height;
					if (falling) {
						falling = false;
					}

				} else {
					if (!falling && !jumping) {
						gravity = 0.0;
						falling = true;
					}
				}

				if (getBoundsLeft().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX() + t.width;
				}
				if (getBoundsRight().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX() - t.width;
				}
			}
		}

		for (int i = 0; i < handler.entitys.size(); i++) {
			Entity e = handler.entitys.get(i);
			if (e.getId() == Id.mushroom) {
				if (getBounds().intersects(e.getBounds())) {
					width *= 2;
					height *= 2;
					e.die();
				}
			}
		}
		if (jumping) {
			gravity -= 0.1;
			setVelY((int) -gravity);
			if (gravity <= 0.0) {
				jumping = false;
				falling = true;
			}
		}
		if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}
		frameDelay++;
		if (frameDelay >= 3) {
			frame++;
			if (frame >= 5) {
				frame = 0;
			}
			frameDelay = 0;
		}

	}

}
