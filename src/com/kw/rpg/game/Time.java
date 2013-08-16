package com.kw.rpg.game;

public class Time 
{
	public static final float DAMPING = 10000000;
	
	private static long curTime;
	private static long lastTime;
	
	public static long getTime()
	{
		return System.nanoTime();
	}
	
	public static float getDelta()
	{
		return (curTime - lastTime) / DAMPING;
	}
	
	public static void update()
	{
		lastTime = curTime;
		curTime = getTime();
	}
	
	public static void init()
	{
		lastTime = getTime();
		curTime = getTime();
	}
}
