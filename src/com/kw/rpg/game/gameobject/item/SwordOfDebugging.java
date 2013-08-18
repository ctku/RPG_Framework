package com.kw.rpg.game.gameobject.item;

public class SwordOfDebugging extends EquippableItem
{
	public static final float SIZE = 32;
	
	private int damage;
	
	public SwordOfDebugging(float x, float y)
	{
		init(x,y,1.0f,0.5f,0,SIZE,SIZE,"The Legendary Sword Of Debuggery", WEAPON_SLOT);
		damage = 3;
	}
}
