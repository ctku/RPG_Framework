package com.kw.rpg.game.gameobject;

public class Stats 
{
	public static final int MAX_LEVEL = 99;
	public static final int MAX_XP = 999999;
	public static final double LEVEL_CONST = MAX_XP/((double)MAX_LEVEL * (double)MAX_LEVEL);
	//public static final double LEVEL_CONST = 25.0 * Math.pow(3.0, (3.0/2.0)); OLD LEVEL CONSTANT
	
	private StatScale scale;
	private float xp;
	private int level;
	private boolean levelable;
	private int health;
	
	public Stats(float xp, boolean levelable)
	{
		this.levelable = levelable;
		scale = new StatScale();
		// WARNING: Entering the land of the temporary code
		scale.generateStatScale();
		// Now leaving the land of temporary code
		
		if (levelable)
		{
			this.xp = xp;
			this.level = 1;
		}
		else
		{
			this.xp = -1;
			this.level = (int)xp;
		}
		health = getMaxHealth();
	}
	
	public int getLevel()
	{
		if (!levelable)
			return level;
		
		// Calculate XP from Level = ax^2
		// Calculate Level from XP = sqrt(XP/a);
		
		return (int)Math.sqrt((double)xp/LEVEL_CONST) + 1;
		
		
		/* OLD LEVEL CALCULATION
		double x = xp + 105.0;
		
		double a = Math.sqrt(243.0 * (x * x) + 4050.0 * x + 17500.0);
		double c = (3.0 * x + 25.0) / 25.0;
		double d = Math.cbrt(a / LEVEL_CONST + c);
		
		return (int)(d - 1.0/d * 3.0) - 1; */
	}

	public int getCurrentHealth()
	{
		int max = getMaxHealth();
		if (health > max)
			health = max;
		
		return health;
	}
	
	public int getMaxHealth()
	{
		return (int)(getLevel() * scale.getScale(StatScale.VITALITY) * 10);
	}
	
	public float getSpeed()
	{
		return (float)(getLevel() * scale.getScale(StatScale.SPEED) * 10);
	}
	
	public float getStrength()
	{
		return (float)(getLevel() * scale.getScale(StatScale.STRENGTH) * 10);
	}
	
	public float getMagic()
	{
		return (float)(getLevel() * scale.getScale(StatScale.MAGIC) * 10);
	}
	
	public float getPhysicalDefense()
	{
		return (float)(getLevel() * scale.getScale(StatScale.PHYSICALDEFENSE) * 10);
	}
	
	public float getMagicDefense()
	{
		return (float)(getLevel() * scale.getScale(StatScale.MAGICDEFENSE) * 10);
	}
	
	public void addXp(float amt)
	{
		xp += amt;
		if (xp > MAX_XP)
			xp = MAX_XP;
	}	
	
	public void damage(int amt)
	{
		health -= amt;
	}
}
