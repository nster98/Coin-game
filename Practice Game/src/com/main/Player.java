package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject
{
	Handler handler;
	
	private BufferedImage player_image;
	
	public Player(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet_small);
		
		player_image = ss.grabImage(2, 1, 32, 32);
	}
	public void tick() 
	{
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 67);
		
		collision();
	}
	public void collision() 
	{
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Coin || tempObject.getId() == ID.MovingCoin || tempObject.getId() == ID.BigCoin) 
				if(getBounds().intersects(tempObject.getBounds())) 
				{
					AudioPlayer.getSound("coin_sound").play();
					HUD.COINS--;
					handler.object.remove(tempObject);
				}
		}
	}
	public void render(Graphics g) 
	{
		//g.setColor(Color.white);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(player_image, x, y, null);
	}
	public Rectangle getBounds() 
	{
		return new Rectangle(x, y, 32, 32);
	} 
	
}
