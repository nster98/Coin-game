package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Coin extends GameObject
{
	Handler handler;
	
	private BufferedImage coin_image;
	
	public Coin(int x, int y, ID id) {
		super(x, y, id);
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet_small);
		
		coin_image = ss.grabImage(1, 2, 16, 20);
	}
	public void tick() 
	{
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 67);
	}
	public void render(Graphics g) 
	{
		//g.setColor(Color.yellow);
		//g.fillOval(x, y, 16, 20);
		g.drawImage(coin_image, x, y, null);
	}
	public Rectangle getBounds() 
	{
		return new Rectangle(x, y, 16, 20);
	} 
	
}
