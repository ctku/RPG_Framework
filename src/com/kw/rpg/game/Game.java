package com.kw.rpg.game;

import java.util.*;

import javax.microedition.khronos.opengles.GL10;
import android.util.Log;
import android.view.MotionEvent;

import com.kw.rpg.engine.GameObject;
import com.kw.rpg.engine.Utility;
import com.kw.rpg.game.gameobject.CookieMonster;
import com.kw.rpg.game.gameobject.Player;
import com.kw.rpg.game.gameobject.item.Cube;

public class Game 
{
	
	private ArrayList<GameObject> objects;
	private ArrayList<GameObject> remove;
	private Player player;
	
	public Game()
	{
		Log.d("Game", "Game()");
		objects = new ArrayList<GameObject>();
		remove = new ArrayList<GameObject>();
		
		player = new Player(Utility.getScreenWidth() / 2 - Player.SIZE / 2, Utility.getScreenHeight() / 2 - Player.SIZE / 2);
		
		objects.add(player);
		objects.add(new Cube(32,32,player));
		objects.add(new CookieMonster(300,500,1));
	}
	
	public void getKeyboardInput(int keyCode)
	{
	     player.getKeyboardInput(keyCode);
	} 
	
	public void getMouseInput(MotionEvent event)
	{
	     player.getMouseInput(event);
	} 
	
	public void update()
	{
		//Log.d("Game", "update()");
		for(GameObject go : objects)
		{
			if (!go.getRemove())
				go.update();
			else
				remove.add(go);
		}
		
		for(GameObject go : remove)
			objects.remove(go);
			
	}
	
	public void render(GL10 gl)
	{
		//Log.d("Game", "render()");
		for(GameObject go : objects)
			go.render(gl);
	}
	
	public ArrayList<GameObject> sphereCollide(float x, float y, float radius)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		for (GameObject go : objects)
		{
			if (Util.dist(go.getX(), go.getY(), x, y) < radius)
				res.add(go);
		}
		
		return res;
	}
}
