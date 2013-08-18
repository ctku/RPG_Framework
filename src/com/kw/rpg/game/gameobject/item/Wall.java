package com.kw.rpg.game.gameobject.item;

import com.kw.rpg.engine.GameObject;

public class Wall extends GameObject
{
	public Wall(float x, float y, float sizeX, float sizeY)
	{
		init(x,y,1.0f,0.5f,0,sizeX,sizeY,DEFAULT_ID);
		setSolid(true);
	}
}
