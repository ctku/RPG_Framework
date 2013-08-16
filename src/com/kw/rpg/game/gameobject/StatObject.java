package com.kw.rpg.game.gameobject;

import com.kw.rpg.engine.GameObject;

public class StatObject extends GameObject
{
	protected Stats stats;
	
	public void damage(int amt)
	{
		stats.damage(amt);
	}

	public float getSpeed()
	{
		return stats.getSpeed();
	}
	
	public int getLevel()
	{
		return stats.getLevel();
	}
	
	public int getMaxHealth()
	{
		return stats.getMaxHealth();
	}
	
	public int getCurrentHealth()
	{
		return stats.getCurrentHealth();
	}
	
	public float getStrength()
	{
		return stats.getStrength();
	}
	
	public float getMagic()
	{
		return stats.getMagic();
	}
}
