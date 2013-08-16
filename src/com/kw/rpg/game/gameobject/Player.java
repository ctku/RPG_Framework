package com.kw.rpg.game.gameobject;

import com.kw.rpg.game.Time;
import com.kw.rpg.game.gameobject.item.Item;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Player extends StatObject {

	private float prevX, prevY;
	
	public static final float SIZE = 32;
	private Inventory inventory;
	
	public Player(float x, float y)
	{
		Log.d("Player", "Player()");
		init(x, y, 0.1f, 1f, 0.25f, SIZE, SIZE, PLAYER_ID);
		stats = new Stats(0, true);
		inventory = new Inventory(20);
		
		prevX = x;
		prevY = y;
	}

	@Override
	public void update()
	{
		//Log.d("Player", "update(in)");
		//System.out.println("Stats: SPEED: " + getSpeed() + " LEVEL:" + getLevel() + " MAXHP:" + getMaxHealth() + " HP:" + getCurrentHealth() + " STRENGTH:" + getStrength() + " MAGIC:" + getMagic());
		//Log.d("Player", "update(out)");
	}
	
	public void getKeyboardInput(int keyCode)
	{
		Log.d("Player","1");
		if(keyCode==KeyEvent.KEYCODE_W)
			move(0,1);
		if(keyCode==KeyEvent.KEYCODE_S)
			move(0,-1);
		if(keyCode==KeyEvent.KEYCODE_A)
			move(-1,0);
		if(keyCode==KeyEvent.KEYCODE_D)
			move(1,0);
		Log.d("Player","2");
	}
	
	public void getMouseInput(MotionEvent event)
	{
		if (event.getAction()==MotionEvent.ACTION_MOVE)
			move(event.getX()-prevX,prevY-event.getY());
		
		prevX = event.getX();
		prevY = event.getY();
	}
	
	private void move(float magX, float magY)
	{
		x += getSpeed() * magX * Time.getDelta();
		y += getSpeed() * magY * Time.getDelta();
	}
	
	public void addItem(Item item)
	{
		inventory.add(item);
	}
	
	public void addXp(float amt)
	{
		stats.addXp(amt);
	}
}
