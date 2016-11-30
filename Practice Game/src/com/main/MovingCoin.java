package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MovingCoin extends GameObject
{
	Handler handler;
	Random r = new Random();
	
	private BufferedImage coin_image;
	
	public MovingCoin(int x, int y, ID id) 
	{
		super(x, y, id);
		
		velX = (r.nextInt(4) + 1);
		velY = (r.nextInt(4) + 1);
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet_small);
		
		coin_image = ss.grabImage(1, 3, 10, 14);
	}
	public void tick() 
	{
		x += velX;
		y += velY;
		
		//x = Game.clamp(x, 0, Game.WIDTH - 37);
		//y = Game.clamp(y, 0, Game.HEIGHT - 67);
		
		if (y <= 0 || y >= Game.HEIGHT - 37) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 67) velX *= -1;
	}
	public void render(Graphics g) 
	{
		//g.setColor(Color.orange);
		//g.fillOval(x, y, 10, 14);
		g.drawImage(coin_image, x, y, null);
	}
	public Rectangle getBounds() 
	{
		return new Rectangle(x, y, 10, 14);
	} 
	
}
