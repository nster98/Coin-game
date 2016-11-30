package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BigCoin extends GameObject
{
	Handler handler;
	Random r = new Random();
	
	private BufferedImage coin_image;
	
	public BigCoin(int x, int y, ID id) {
		super(x, y, id);
		
		velX = (r.nextInt(10) + 5);
		velY = (r.nextInt(10) + 5);
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet_small);
		
		coin_image = ss.grabImage(1, 4, 23, 32);
	}
	public void tick() 
	{
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 37) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 67) velX *= -1;
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
