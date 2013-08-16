package com.kw.rpg.engine;

import android.graphics.Rect;

public class Physics {

	// go1:itself, go2:to be test
	public static GameObject checkCollision(GameObject go1, GameObject go2)
	{
		Rect r1 = new Rect((int)go1.getX(),(int)go1.getY(),(int)(go1.getX()+go1.getSX()-1),(int)(go1.getY()+go1.getSY()-1));
		Rect r2 = new Rect((int)go2.getX(),(int)go2.getY(),(int)(go2.getX()+go2.getSX()-1),(int)(go2.getY()+go2.getSY()-1));

		boolean res = r1.intersect(r2);
		
		if (res)
			return go2;
		else
			return null;
	}
}
