package com.kw.rpg.game.gameobject.item;

import android.util.Log;
import com.kw.rpg.game.gameobject.Player;

public class Cube extends Item
{
	public static final float SIZE = 32;

	public Cube(float x, float y, Player play)
	{
		super(play);
		Log.d("Cube", "Cube()");
		init(x,y,1.0f,0.5f,0,SIZE,SIZE,"The Cube");
	}
}
