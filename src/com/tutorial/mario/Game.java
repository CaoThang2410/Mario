package com.tutorial.mario;
import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.tutorial.mario.entity.Player;
import com.tutorial.mario.input.KeyInput;
import com.tutorial.mario.tile.Wall;
import com.tutorial.mario.*;
public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH/14*10;
	public static final int scale = 4;
	public static final String title="Super Mario R";
	
	private Thread thread;
	private boolean running = false;
	private Graphics g ;
	
	public static Handler handler;
	private void init()
	{
		handler = new Handler();
		addKeyListener(new KeyInput());
		
		handler.addEntity(new Player(300,512,64,64,true,Id.player,handler));
		handler.addTile(new Wall(200,200,64,64,true,Id.wall,handler));
	}
	
	public synchronized void start()
	{
		if(running) return;
		running = true;
		thread = new Thread(this,"Thread");
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running) return;
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
		double ns = 1000000000.0/60.0;
		int frames = 0;
		int ticks = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1)
			{
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000)
			{
				timer+=1000;
				System.out.println(frames +" Frames per second "+ ticks + " Update per second");
				frames = 0;
				ticks = 0;
			}
		}
		stop();
		
	}
	 @Override
	   public void paint(Graphics g){
	     
	   }

	   private void drawWhatEver(Graphics2D g2){
	       g2.fillRect(20, 50, 20, 20);//now.. this I can show..
	   }
	 
	public void render()
	{
		BufferStrategy bs =  getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.PINK);
		g.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g);
		g.dispose();
		bs.show();
		
			
			
		
	}
	
	public void tick()
	{
		handler.tick();
	}
	
	public Game()
	{
		Dimension size = new Dimension(WIDTH*scale,HEIGHT*scale);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		JFrame windown =new JFrame();
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
