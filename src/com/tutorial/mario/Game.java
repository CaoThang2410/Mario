package com.tutorial.mario;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.gfx.Sprite;
import com.tutorial.mario.gfx.SpriteSheet;
import com.tutorial.mario.input.KeyInput;
import com.tutorial.mario.tile.Wall;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH / 14 * 10;
	public static final int scale = 4;
	public static final String title = "Super Mario R";

	private Thread thread;
	private boolean running = false;
	private Graphics g;
	public BufferedImage image;

	public class ImageLoader {
		public BufferedImage loadImage(String path) {
			try {
				image = ImageIO.read(getClass().getResource(path));

			} catch (IOException e) {
				e.printStackTrace();
			}
			return image;
		}
	}

	public static Handler handler;

	public static SpriteSheet sheet1, sheet2, sheet3,sheetmushroom;

	public static Sprite grass;
	public static Sprite player[] = new Sprite[10];

	public static Sprite mushroom;
	public static Camera cam;

	private void init() {
		handler = new Handler();

		sheet1 = new SpriteSheet("/map/Terrain (16x16).png");
		sheet2 = new SpriteSheet("/player/Fall (32x32).png");
		sheet3 = new SpriteSheet("/player/Run (32x32).png");
		sheetmushroom = new SpriteSheet("/Item/potion_red.png");
		addKeyListener(new KeyInput());
		grass = new Sprite(sheet1, 4, 1);
		cam = new Camera();
		for (int i = 0; i < player.length; i++) {
			player[i] = new Sprite(sheet3, i + 1, 1);
		}
		//mushroom = new Sprite(sheetmushroom, 1, 1);
	
		/*
		 * // code trước đó chạy đc handler.crateLevel(image); handler.addEntity(new
		 * Player(300,512, 64, 64, true, Id.player, handler));
		 */
//		 handler.addEntity(new Player(300, 512, 64, 64, true, Id.player, handler));
//		 handler.addTile(new Wall(200,200,64,64,true,Id.wall,handler));
		try {
			image = ImageIO.read(getClass().getResource("/map/test2.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		handler.createLevel(image);

	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		int frames = 0;
		int ticks = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(frames + " Frames per second " + ticks + " Update per second");
				frames = 0;
				ticks = 0;
			}
		}
		stop();

	}

	@Override
	public void paint(Graphics g) {

	}

	private void drawWhatEver(Graphics2D g2) {
		g2.fillRect(20, 50, 20, 20);// now.. this I can show..
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.PINK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(cam.getX(), cam.getY());
		handler.render(g);
		g.dispose();
		bs.show();

	}

	public void tick() {
		handler.tick();

		for (Entity e : handler.entitys) {
			if (e.getId() == Id.player) {
				cam.tick(e);
			}
		}
	}

	public static int getFrameWidth() {
		return WIDTH * scale;
	}

	public static int getFrameHeight() {
		return HEIGHT * scale;
	}

	public Game() {
		Dimension size = new Dimension(WIDTH * scale, HEIGHT * scale);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame windown = new JFrame();
		windown.setTitle(title);
		windown.add(game);
		windown.pack();
		windown.setResizable(false);
		windown.setLocationRelativeTo(null);
		windown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windown.setVisible(true);
		game.start();

	}
}
