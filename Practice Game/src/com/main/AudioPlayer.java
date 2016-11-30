package com.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer 
{
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() 
	{
		try 
		{
			soundMap.put("play_sound", new Sound("res/Menu8-Bit.ogg"));
			soundMap.put("coin_sound", new Sound("res/Coin01.ogg"));
			musicMap.put("menu_music", new Music("res/background.ogg"));
			musicMap.put("game_music", new Music("res/Voyage.ogg"));
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	public static Music getMusic(String key) 
	{
		return musicMap.get(key);
	}
	public static Sound getSound(String key) 
	{
		return soundMap.get(key);
	}
	public static void endMusic(String key) 
	{
		musicMap.clear();
	}
	public static Sound endSound(String key) 
	{
		return soundMap.remove(key);
	}
}
