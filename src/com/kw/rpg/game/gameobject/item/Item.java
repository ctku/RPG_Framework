package com.kw.rpg.game.gameobject.item;

import android.util.Log;

import com.kw.rpg.engine.GameObject;
import com.kw.rpg.engine.Sprite;

public class Item extends GameObject
{
	protected String name;
	
	public String getName()
	{
		return name;
	}
	
	protected void init(float x, float y, float r, float g, float b, float sx, float sy, String name)
	{
		Log.d("Item", "init()");
		this.x = x;
		this.y = y;
		this.type = ITEM_ID;
		this.spr = new Sprite(r,g,b,sx,sy);
		this.name = name;
	}
}
