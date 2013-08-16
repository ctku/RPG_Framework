package com.kw.rpg.engine;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public class Animation {

	private ArrayList<Frame> frames;
	private int curFrame;
	
	public Animation()
	{
		frames = new ArrayList<Frame>();
	}
	
	public void render(GL10 gl)
	{
		Frame temp = frames.get(curFrame);
		if (temp.render(gl))
		{
			curFrame++;
			curFrame %= frames.size();
		}
	}
}
