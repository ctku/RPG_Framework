package com.kw.rpg.engine;

import javax.microedition.khronos.opengles.GL10;

public class Frame {

	private int length;
	private Sprite spr;
	private int numDisplayed;
	
	public Frame(Sprite spr, int length) 
	{
		this.spr = spr;
		this.length = length;
		numDisplayed = 0;
	}
	
	public boolean render(GL10 gl)
	{
		spr.render(gl);
		numDisplayed++;
		
		if (numDisplayed >= length)
		{
			numDisplayed = 0;
			return true;
		}
		
		return false;
	}
}
