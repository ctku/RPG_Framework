package com.kw.rpg.game;

import java.util.*;

import javax.microedition.khronos.opengles.GL10;
import android.util.Log;
import android.view.MotionEvent;

import com.kw.rpg.engine.GameObject;
import com.kw.rpg.engine.Physics;
import com.kw.rpg.engine.Rectangle;
import com.kw.rpg.engine.Utility;
import com.kw.rpg.game.gameobject.CookieMonster;
import com.kw.rpg.game.gameobject.Player;
import com.kw.rpg.game.gameobject.item.Cube;
import com.kw.rpg.game.gameobject.item.Wall;

public class Game 
{
	public static Game game;
	
	private ArrayList<GameObject> objects;
	private ArrayList<GameObject> remove;
	private Player player;
	
	public void generateTestLevel()
	{
		int xx = 200;
		int yy = 500;
		//Generate First Room
		objects.add(new Wall(200+xx,200+yy,1,300));
		objects.add(new Wall(500+xx,200+yy,1,100));
		objects.add(new Wall(500+xx,400+yy,1,100));
		objects.add(new Wall(200+xx,200+yy,300,1));
		objects.add(new Wall(200+xx,500+yy,100,1));
		objects.add(new Wall(400+xx,500+yy,100,1));
		
		//Generate Hallway 1
		objects.add(new Wall(300+xx,500+yy,1,200));
		objects.add(new Wall(400+xx,500+yy,1,200));
		
		//Generate Second Room
		objects.add(new Wall(200+xx,700+yy,1,300));
		objects.add(new Wall(500+xx,700+yy,1,300));
		objects.add(new Wall(200+xx,1000+yy,300,1));
		objects.add(new Wall(200+xx,700+yy,100,1));
		objects.add(new Wall(400+xx,700+yy,100,1));
		
		//Generate Hallway 2
		objects.add(new Wall(500+xx,400+yy,100,1));
		objects.add(new Wall(500+xx,300+yy,100,1));
		
		//Generate Third Room
		objects.add(new Wall(600+xx,200+yy,1,100));
		objects.add(new Wall(600+xx,400+yy,1,100));
		objects.add(new Wall(600+xx,200+yy,300,1));
		objects.add(new Wall(600+xx,500+yy,300,1));
		objects.add(new Wall(900+xx,200+yy,1,300));
	}
	
	public Game()
	{
		Log.d("Game", "Game()");
		objects = new ArrayList<GameObject>();
		remove = new ArrayList<GameObject>();
		
		player = new Player(Utility.getScreenWidth() / 2 - Player.SIZE / 2, Utility.getScreenHeight() / 2 - Player.SIZE / 2);
		
		objects.add(player);
		generateTestLevel();
		//objects.add(new Cube(32,32));
		//objects.add(new CookieMonster(300,500,1));
		//objects.add(new Wall(200,200,1,300));
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
	
	public ArrayList<GameObject> getObjects()
	{
		return objects;
	}
	
	public static ArrayList<GameObject> sphereCollide(float x, float y, float radius)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		for (GameObject go : game.getObjects())
		{
			if (Util.dist(go.getX(), go.getY(), x, y) < radius)
				res.add(go);
		}
		
		return res;
	}
	
	public static ArrayList<GameObject> rectangleCollide(float x1, float y1, float x2, float y2)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		float sx = x2 - x1;
		float sy = y2 - y1;
		
		Rectangle collider = new Rectangle((int)x1, (int)y1, (int)sx, (int)sy);
		
		for (GameObject go : game.getObjects())
		{
			if (Physics.checkCollision(collider, go) != null)
				res.add(go);
		}
		
		return res;
	}
}
