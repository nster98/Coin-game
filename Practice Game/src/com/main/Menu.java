package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.Game.STATE;

public class Menu extends MouseAdapter
{
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) 
	{
		this.game = game;
		this.hud = hud;
		this.handler = handler;
		
	}
	public void mousePressed(MouseEvent e) 
	{
		int mx = e.getX();
		int my = e.getY();
		
		if (Game.gameState == STATE.Menu) 
		{
			//play button
			if(mouseOver(mx, my, 400, 300, 500, 150)) 
			{
				hud.setLevel(1);
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				
				for (int i = 0; i < 20; i++)
				{	
					handler.addObject(new Coin((r.nextInt(Game.WIDTH)), (r.nextInt(Game.HEIGHT)), ID.Coin));
				}
				AudioPlayer.getSound("play_sound").play();
			}
			//help button
			if (mouseOver(mx, my, 400, 500, 500, 150)) 
			{
				Game.gameState = STATE.Help;
				AudioPlayer.getSound("play_sound").play();
			}
			
			//exit button
			if(Game.gameState == STATE.Menu)
			{
				if(mouseOver(mx, my, 400, 700, 500, 150))
				{
					AudioPlayer.getSound("play_sound").play();
					System.exit(0);
				}
			}	
		}
		//back button for help screen
		if (Game.gameState == STATE.Help)
		{
			if(mouseOver(mx, my, 400, 700, 500, 150))
			{
				Game.gameState = STATE.Menu;
				AudioPlayer.getSound("play_sound").play();
			}
		}	
		//back to menu from game over screen
		if (Game.gameState == STATE.End)
			if (mouseOver(mx, my, 400, 700, 500, 150)) 
			{
				hud.setLevel(1);
				HUD.COINS = 20;
				Game.timer = 1200;
				//HUD.timer = 0;
				//Spawn.timer = 0;
				Game.gameState = STATE.Menu;
				AudioPlayer.getSound("play_sound").play();
			}
	}
	public void mouseReleased(MouseEvent e) 
	{
		
	}
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) 
	{
		if (mx > x && mx < x + width)
			if (my > y && my < y + height)
				return true;
			else return false;
		else return false;
	}
	public void tick() 
	{

	}
	public void render(Graphics g) 
	{
		if (Game.gameState == STATE.Menu) 
		{
			Font fnt = new Font("Helvetica", 1, 72);
			Font fnt2 = new Font("Helvetica", 1, 65);
			
			//g.setFont(fnt);
			//g.setColor(Color.yellow);
			//g.drawString("Catch Me If You Coin", 300, 160);
			g.drawImage(Game.game_logo, 475, 10, null);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(400, 700, 500, 150);
			g.drawString("Quit", 570, 800);
			
			g.setColor(Color.white);
			g.drawRect(400, 500, 500, 150);
			g.drawString("Help", 570, 600);
			
			g.setColor(Color.white);
			g.drawRect(400, 300, 500, 150);
			g.drawString("Play", 570, 400);
		}
		else if (Game.gameState == STATE.Help) 
		{
			Font fnt = new Font("Helvetica", 1, 72);
			Font fnt2 = new Font("Helvetica", 1, 65);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 570, 150);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(400, 700, 500, 150);
			g.drawString("Use WASD to move", 350, 350);
			g.drawString("Collect all the coins before", 250, 475);
			g.drawString("time runs out!", 430, 550);
			g.drawString("Back", 570, 800);
		}
		else if (Game.gameState == STATE.End) 
		{
			Font fnt = new Font("Helvetica", 1, 72);
			Font fnt2 = new Font("Helvetica", 1, 65);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 460, 150);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(400, 700, 500, 150);
			g.drawString("You made it to level " + (hud.getLevel() - 1), 310, 475);
			g.drawString("Menu", 570, 800);
		}
	}
}
