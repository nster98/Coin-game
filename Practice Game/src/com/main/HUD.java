package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD 
{
	public static int COINS = 20;
	
	private int level = 1;
	
	public void tick() 
	{
		
	}
	public void render(Graphics g) 
	{
		Font myFont = new Font("Helvetica", 1, 24);
		Font myFont2 = new Font("Helvetica", 1, 64);
		
		g.setFont(myFont);
		g.setColor(Color.white);
		g.drawString("Level: " + level, 15, 75);
		
		g.drawString("Coins left: " + COINS, 15, 35);
		
		if ((Game.timer / 60) == 0)
		{
			Game.timer = 1200;
		}
		g.setFont(myFont2);
		g.drawString("" + (Game.timer / 60), Game.WIDTH / 2, Game.HEIGHT - (Game.HEIGHT - 60));
	}
	public void setLevel(int level) 
	{
		this.level = level;
	}
	public int getLevel() 
	{
		return level;
	}
	public void setCoins(int coins) 
	{
		HUD.COINS = coins;
	}
	public int getCoins() 
	{
		return HUD.COINS;
	}
}
