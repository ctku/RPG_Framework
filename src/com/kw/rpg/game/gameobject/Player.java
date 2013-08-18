package com.kw.rpg.game.gameobject;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.kw.rpg.engine.GameObject;
import com.kw.rpg.engine.Utility;
import com.kw.rpg.game.Delay;
import com.kw.rpg.game.Game;
import com.kw.rpg.game.Time;
import com.kw.rpg.game.Util;
import com.kw.rpg.game.gameobject.item.Item;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Player extends StatObject {

	// for mouse event
	private float prevX, prevY;
	private Delay clickDelay;
	public static final int MAX_KEY_MOVE = 10;
	public static final int MAX_MOUSE_MOVE = 1;
	
	public static final float SIZE = 32;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private Inventory inventory;
	private Equipment equipment;
	
	private int attackRange;
	private int facingDirection;
	private Delay attackDelay;
	private int attackDamage;
	
	private int moveAmountX;
	private int moveAmountY;
	
	public Player(float x, float y)
	{
		init(x, y, 0.1f, 1f, 0.25f, SIZE, SIZE, PLAYER_ID);
		stats = new Stats(0, true);
		inventory = new Inventory(20);
		equipment = new Equipment(inventory);
		attackDelay = new Delay(500);
		attackRange = 300;
		attackDamage = 1;
		facingDirection = 0;
		moveAmountX = 0;
		moveAmountY = 0;
		attackDelay.terminate();
		
		clickDelay = new Delay(200);
		clickDelay.terminate();
		prevX = x;
		prevY = y;
	}

	@Override
	public void update()
	{
		//System.out.println("Stats: SPEED: " + getSpeed() + " LEVEL:" + getLevel() + " MAXHP:" + getMaxHealth() + " HP:" + getCurrentHealth() + " STRENGTH:" + getStrength() + " MAGIC:" + getMagic());
		float newX = x + (float)moveAmountX;
		float newY = y + (float)moveAmountY;
		
		moveAmountX = 0;
		moveAmountY = 0;
		
		ArrayList<GameObject> objects = Game.rectangleCollide(newX, newY, newX+SIZE, newY+SIZE);
		ArrayList<GameObject> items = new ArrayList<GameObject>();
		
		boolean move = true;
		
		for (GameObject go : objects)
		{
			if (go.getType() == GameObject.ITEM_ID)
				items.add(go);
			if (go.getSolid())
				move = false;
		}
		
		if (!move)
			return;
			
		x = newX;
		y = newY;
		
		for (GameObject go : items)
		{
			System.out.println("You just picked up " + ((Item)go).getName() + "!");
			go.remove();
			addItem((Item)go);
		}
	}
	
	public void getKeyboardInput(int keyCode)
	{
		if (keyCode==KeyEvent.KEYCODE_W)
			move(0,MAX_KEY_MOVE);
		if (keyCode==KeyEvent.KEYCODE_S)
			move(0,-MAX_KEY_MOVE);
		if (keyCode==KeyEvent.KEYCODE_A)
			move(-MAX_KEY_MOVE,0);
		if (keyCode==KeyEvent.KEYCODE_D)
			move(MAX_KEY_MOVE,0);
		if ((keyCode==KeyEvent.KEYCODE_SPACE) && attackDelay.isOver())
			attack();
	}
	
	public void getMouseInput(MotionEvent event)
	{
		// handle click
		if (event.getAction()==MotionEvent.ACTION_DOWN)
			clickDelay.restart();
		if ((event.getAction()==MotionEvent.ACTION_UP) && !clickDelay.isOver())
			attack();
		
		// handle move
		if (event.getAction()==MotionEvent.ACTION_MOVE)
		{
			float moveX = event.getX()-prevX;
			float moveY = prevY-event.getY();
			float signX = Math.signum(moveX);
			float signY = Math.signum(moveY);
			
			moveX = Math.min(MAX_KEY_MOVE, Math.abs(moveX));
			moveY = Math.min(MAX_KEY_MOVE, Math.abs(moveY));
			move(signX*moveX, signY*moveY);
		}
		prevX = event.getX();
		prevY = event.getY();
	}
	
	public void attack()
	{
		//Find objects in attack range
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		
		if (facingDirection == UP)
			objects = Game.rectangleCollide(x-SIZE/2f, y-SIZE/2f, x+SIZE/2f, y+attackRange);
		else if (facingDirection == DOWN)
			objects = Game.rectangleCollide(x-SIZE/2f, y-attackRange, x+SIZE/2f, y+SIZE/2f);
		else if (facingDirection == LEFT)
			objects = Game.rectangleCollide(x-attackRange, y-SIZE/2f, x+SIZE/2f, y+SIZE/2f);
		else if (facingDirection == RIGHT)
			objects = Game.rectangleCollide(x-SIZE/2f, y-SIZE/2f, x+attackRange, y+SIZE/2f);
		
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
		
		
		attackDelay.restart();
	}
	
	@Override
	public void render(GL10 gl)
	{
		gl.glTranslatef(Utility.getScreenWidth() / 2 - Player.SIZE / 2, Utility.getScreenHeight() / 2 - Player.SIZE / 2,0);
		spr.render(gl);
		gl.glTranslatef(-x,-y,0);
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
		
		moveAmountX += 1f * magX * Time.getDelta(); //TODO: Add speed based scaling
		moveAmountY += 1f * magY * Time.getDelta();
		
	}
}
