package com.main;

import java.util.Random;

public class Spawn 
{
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Spawn(Handler handler, HUD hud) 
	{
		this.handler = handler;
		this.hud = hud;
	}
	public void tick() 
	{
		boolean nl = false;
		if(HUD.COINS == 0)
		{
			hud.setLevel(hud.getLevel() + 1);
			nl = true;
		}
		
		if (Game.timer / 60 == 0 || nl) 
		{	
			Game.timer = 1200;
			if (!nl)
				hud.setLevel(hud.getLevel() + 1);
			
			if (HUD.COINS == 0)
			{
				if (hud.getLevel() == 2)
				{
					for (int i = 0; i < 25; i++) 
					{
						handler.addObject(new Coin(r.nextInt(Game.WIDTH + 25), r.nextInt(Game.HEIGHT + 25), ID.Coin));
						HUD.COINS = 25;
					}
				}
				if (hud.getLevel() == 3)
					for (int i = 0; i < 30; i++) 
					{
						handler.addObject(new Coin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.Coin));
						HUD.COINS = 30;
					}
				if (hud.getLevel() == 4)
					for (int i = 0; i < 35; i++) 
					{
						handler.addObject(new Coin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.Coin));
						HUD.COINS = 35;
					}
				if (hud.getLevel() == 5)
					for (int i = 0; i < 40; i++) 
					{
						handler.addObject(new Coin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.Coin));
						HUD.COINS = 40;
					}
				if (hud.getLevel() == 6) 
				{
					for (int i = 0; i < 20; i++) 
					{
						handler.addObject(new Coin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.Coin));
						HUD.COINS = 25;
					}
					for (int i = 0; i < 5; i++)
					{
						handler.addObject(new MovingCoin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.MovingCoin));
						HUD.COINS = 25;
					}
				}	
				if (hud.getLevel() == 7)
					for (int i = 0; i < 20; i++) 
					{
						handler.addObject(new MovingCoin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.MovingCoin));
						HUD.COINS = 20;
					}
				if (hud.getLevel() == 8)
					for (int i = 0; i < 50; i++) 
					{
						handler.addObject(new Coin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.Coin));
						HUD.COINS = 50;
					}
				if (hud.getLevel() == 9)
				{
					for (int i = 0; i < 24; i++) 
					{
						handler.addObject(new Coin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.Coin));
						HUD.COINS = 25;
					}
					for (int i = 0; i < 1; i++)
					{
						handler.addObject(new BigCoin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.BigCoin));
						HUD.COINS = 25;
					}
				}
				if (hud.getLevel() == 10)
					for (int i = 0; i < 10; i++) 
					{
						handler.addObject(new BigCoin(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 25), ID.BigCoin));
						HUD.COINS = 10;
					}
				if (hud.getLevel() == 11) 
				{
					Game.gameState = Game.STATE.End;
					Game.timer = 1200;
					handler.clearCoins();
					System.out.println("Good job you won");
				}
				
			}
			else
			{
				Game.gameState = Game.STATE.End;
				Game.timer = 1200;
				handler.clearCoins();
			}
		}
	}
}
