package com.kw.rpg.engine;

import com.kw.rpg.engine.Rectangle;

public class Physics {

	// go1:itself, go2:to be tested
	public static GameObject checkCollision(GameObject go1, GameObject go2)
	{
		return checkCollision(new Rectangle((int)go1.getX(), (int)go1.getY(), (int)go1.getSX(), (int)(go1.getSY())), go2);
	}
	
	public static GameObject checkCollision(Rectangle r1, GameObject go2)
	{
		Rectangle r2 = new Rectangle((int)go2.getX(), (int)go2.getY(), (int)go2.getSX(), (int)go2.getSY());

		boolean res = r1.intersect(r2);
		
		if (res)
			return go2;
		else
			return null;
	}
}
