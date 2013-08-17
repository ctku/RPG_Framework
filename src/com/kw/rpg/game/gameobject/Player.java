package com.kw.rpg.game.gameobject;

import java.util.ArrayList;

import com.kw.rpg.engine.GameObject;
import com.kw.rpg.engine.MainActivity;
import com.kw.rpg.game.Delay;
import com.kw.rpg.game.Time;
import com.kw.rpg.game.Util;
import com.kw.rpg.game.gameobject.item.Item;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Player extends StatObject {

	// for mouse event
	private float prevX, prevY;
	private Delay clickDelay;
	
	public static final float SIZE = 32;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private Inventory inventory;
	
	private int attackRange;
	private int facingDirection;
	private Delay attackDelay;
	private int attackDamage;
	
	public Player(float x, float y)
	{
		init(x, y, 0.1f, 1f, 0.25f, SIZE, SIZE, PLAYER_ID);
		stats = new Stats(0, true);
		inventory = new Inventory(20);
		attackDelay = new Delay(500);
		attackRange = 300;
		attackDamage = 1;
		facingDirection = 0;
		attackDelay.end();
		
		clickDelay = new Delay(200);
		clickDelay.end();
		prevX = x;
		prevY = y;
	}

	@Override
	public void update()
	{
		//System.out.println("Stats: SPEED: " + getSpeed() + " LEVEL:" + getLevel() + " MAXHP:" + getMaxHealth() + " HP:" + getCurrentHealth() + " STRENGTH:" + getStrength() + " MAGIC:" + getMagic());
	}
	
	public void getKeyboardInput(int keyCode)
	{
		if (keyCode==KeyEvent.KEYCODE_W)
			move(0,1);
		if (keyCode==KeyEvent.KEYCODE_S)
			move(0,-1);
		if (keyCode==KeyEvent.KEYCODE_A)
			move(-1,0);
		if (keyCode==KeyEvent.KEYCODE_D)
			move(1,0);
		if ((keyCode==KeyEvent.KEYCODE_SPACE) && attackDelay.over())
			attack();
	}
	
	public void getMouseInput(MotionEvent event)
	{
		// handle click
		if (event.getAction()==MotionEvent.ACTION_DOWN)
			clickDelay.start();
		if ((event.getAction()==MotionEvent.ACTION_UP) && !clickDelay.over())
			attack();
		
		// handle move
		if (event.getAction()==MotionEvent.ACTION_MOVE)
			move(event.getX()-prevX,prevY-event.getY());
		prevX = event.getX();
		prevY = event.getY();
	}
	
	public void attack()
	{
		//Find objects in attack range
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		
		if (facingDirection == UP)
			objects = MainActivity.rectangleCollide(x-SIZE/2f, y-SIZE/2f, x+SIZE/2f, y+attackRange);
		else if (facingDirection == DOWN)
			objects = MainActivity.rectangleCollide(x-SIZE/2f, y-attackRange, x+SIZE/2f, y+SIZE/2f);
		else if (facingDirection == LEFT)
			objects = MainActivity.rectangleCollide(x-attackRange, y-SIZE/2f, x+SIZE/2f, y+SIZE/2f);
		else if (facingDirection == RIGHT)
			objects = MainActivity.rectangleCollide(x-SIZE/2f, y-SIZE/2f, x+attackRange, y+SIZE/2f);
		
		//Find which objects are enemies
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		
		for (GameObject go : objects)
		{
			if (go.getType() == ENEMY_ID)
				enemies.add((Enemy)go);
		}

		//Find closest enemy, if one exists
		if (enemies.size() > 0)
		{
			Enemy target = enemies.get(0);
			
			if (enemies.size() > 1)
			{
				for (Enemy e : enemies)
				{
					if (Util.dist(x, y, e.getX(), e.getY()) < Util.dist(x, y, target.getX(), target.getY()))
						target = e;
				}
			}

			//Attack the enemy
			target.damage(attackDamage);
			System.out.println(" : " + target.getCurrentHealth() + "/" + target.getMaxHealth());
		}
		else
			System.out.println(" : No target");
		
		
		attackDelay.start();
	}
	
	public void addItem(Item item)
	{
		inventory.add(item);
	}
	
	public void addXp(float amt)
	{
		stats.addXp(amt);
	}
	
	private void move(float magX, float magY)
	{
		if (Math.abs(magX) > Math.abs(magY))
		{
			if (magX > 0)
				facingDirection = RIGHT;
			else
				facingDirection = LEFT;
		}		
		else
		{
			if (magY > 0)
				facingDirection = UP;
			else
				facingDirection = DOWN;
		}
		//Log.d("face", ""+facingDirection);
		
		x += getSpeed() * magX * Time.getDelta();
		y += getSpeed() * magY * Time.getDelta();
	}
}
