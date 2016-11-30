package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable 
{
	private static final long serialVersionUID = 4088146271165387233L;
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private BufferedImage background;
	
	public enum STATE 
	{
		Menu, Game, Help, End;
	}
	public static STATE gameState = STATE.Menu;
	
	public static BufferedImage game_background, game_logo;
	public static BufferedImage sprite_sheet_small;
	
	public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
	
	public Game() 
	{
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		AudioPlayer.load();
		AudioPlayer.getMusic("game_music").loop();
		
		new Window(WIDTH, HEIGHT, "Catch Me If You Coin - BETA", this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		game_background = loader.loadImage("/space.png");
		sprite_sheet_small = loader.loadImage("/sprite_sheet_small.png");
		game_logo = loader.loadImage("/CatchMeIfYouCoin.png");
		
		SpriteSheet ss = new SpriteSheet(game_background);
		
		background = ss.grabImage(1, 1, 1280, 960);
		
		spawner = new Spawn(handler, hud);
		r = new Random();
		
		/*if(gameState == STATE.Game) 
		{
			HUD.COINS = 20;
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			
			for (int i = 0; i < 20; i++)
			{	
				handler.addObject(new Coin((r.nextInt(WIDTH)), (r.nextInt(HEIGHT)), ID.Coin));
			}
		}*/
	}
	public synchronized void start() 
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() 
	{
		try 
		{
			thread.join();
			running = false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void run() 
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while (running) 
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) 
			{
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) 
			{
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	public static int timer = 1200;
	
	private void tick() 
	{
		handler.tick();
		
		if (gameState == STATE.Game)
		{
			timer--;
			hud.tick();
			spawner.tick();
			
			if (timer / 60 == 0) 
			{
				timer = 1200;
				
				if (HUD.COINS != 0) 
				{
					gameState = STATE.End;
					timer = 1200;
					handler.clearCoins();
				}
			}
		}
		else if (gameState == STATE.Menu || gameState == STATE.End) 
		{
			menu.tick();
		}
	}
	private void render() 
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) 
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//g.setColor(Color.gray);
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(background, getX(), getY(), null);
		
		handler.render(g);
		
		if(gameState == STATE.Game) 
		{
			hud.render(g);
		}
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) 
		{
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}
	public static int clamp(int var, int min, int max) 
	{
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	public static void main(String[] args) 
	{
		new Game();
	}
}
