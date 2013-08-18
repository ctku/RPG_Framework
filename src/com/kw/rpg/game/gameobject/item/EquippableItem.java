package com.kw.rpg.game.gameobject.item;

import com.kw.rpg.engine.Sprite;

public class EquippableItem extends Item
{
	public static final int NUM_SLOTS = 4;
	
	public static final int WEAPON_SLOT = 0;
	public static final int HEAD_SLOT = 1;
	public static final int BODY_SLOT = 2;
	public static final int LEG_SLOT = 3;
	
	private int slot;
	
	protected void init(float x, float y, float r, float g, float b, float sx, float sy, String name, int slot)
	{
		this.x = x;
		this.y = y;
		this.type = ITEM_ID;
		this.slot = slot;
		this.spr = new Sprite(r,g,b,sx,sy);
		this.name = name;
	}

	public int getSlot() {
		return slot;
	}

}
