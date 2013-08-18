package com.kw.rpg.game.gameobject;

import com.kw.rpg.game.RPGRandom;

public class StatScale 
{
	public static final int NUM_STATS = 8;
	public static final double MIN_STATSCALE = 0.125;
	public static final double MAX_STAT_BOOST = 0.25;
	
	public static final int VITALITY = 0;
	public static final int SPEED = 1;
	//public static final int UNKNOWN1 = 2;
	//public static final int UNKNOWN2 = 3;
	public static final int STRENGTH = 4;
	public static final int PHYSICALDEFENSE = 5;
	public static final int MAGIC = 6;
	public static final int MAGICDEFENSE= 7;
	
	private double[] scales;
	private double[] scaleBonus;
	private RPGRandom rand;
	
	public StatScale()
	{
		scales = new double[NUM_STATS];
		scaleBonus = new double[NUM_STATS];
		rand = new RPGRandom();
	}

	public void generateStatScale()
	{
		double sum = 0;
		
		for (int i=0; i<NUM_STATS; i++)
		{
			double val = Math.abs(rand.nextDouble());
			scales[i] = val;
			sum += val * val;
		}
		
		// Normalize scales
		for (int i=0; i<NUM_STATS; i++)
		{
			scales[i] /= sum;
			scales[i] *= (1.0 - MIN_STATSCALE * MIN_STATSCALE * NUM_STATS);
			scales[i] += MIN_STATSCALE * MIN_STATSCALE;
			scales[i] = Math.sqrt(scales[i]);
		}
	}
	
	public double getScale(int stat)
	{
		return scales[stat] + (scaleBonus[stat] * MAX_STAT_BOOST);
	}
	
	public void addScaleBonus(int stat, double bonus)
	{
		if (bonus > 1)
			bonus = 1;
		if (bonus < 0)
			bonus = 0;
		
		scaleBonus[stat] = bonus;
	}

}
