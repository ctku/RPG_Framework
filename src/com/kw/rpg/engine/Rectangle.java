package com.kw.rpg.engine;

import android.graphics.Rect;

public class Rectangle
{
	public int x;
	public int y;
	public int w;
	public int h;
	
	public Rectangle(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean intersect(Rectangle r)
	{
		Rect r1 = new Rect(x, y, x+w-1, y+h-1);
		Rect r2 = new Rect(r.x, r.y, r.x+r.w-1, r.y+r.h-1);
		
		return r1.intersect(r2);
	}
}
