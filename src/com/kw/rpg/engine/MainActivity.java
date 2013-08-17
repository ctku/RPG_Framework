package com.kw.rpg.engine;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.kw.base.R;
import com.kw.rpg.engine.Renderer.RendererCallback;
import com.kw.rpg.game.Game;
import com.kw.rpg.game.Time;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MainActivity extends Activity 
{
	private static Game game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		main();	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void main()
	{
		initUtil();
		initGame();//create stat
		initDisplay();
		
		gameLoop();
	}

	public static ArrayList<GameObject> sphereCollide(float x, float y, float radius)
	{
		return game.sphereCollide(x, y, radius);
	}
	
	public static ArrayList<GameObject> rectangleCollide(float x1, float y1, float x2, float y2)
	{
		return game.rectangleCollide(x1, y1, x2, y2);
	}
	
	private static void initGame()
	{
		game = new Game();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		game.getKeyboardInput(keyCode);
		
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		game.getMouseInput(event);
		
		return true;
	}
	
	public static void update()
	{
		game.update();
	}
	
	public static void render(GL10 gl)
	{
		game.render(gl);
	}
	
	
	public static void gameLoop()
	{
		Time.init();
		
		RendererCallback callbackObj = new RendererCallback() {
			public void func(GL10 gl) {
				// gameLoop
				Time.update();
				update();
				render(gl);
			}
		};
		Renderer.setCallback(callbackObj);
	}
	
	
	/*
	private static void initGL()
	{
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0,Display.getWidth(),0,Display.getHeight(),-1,1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		
		gl.glDisable(GL10.GL_DEPTH_TEST);
		
		gl.glClearColor(0,0,0,0);
	}*/

	private void initUtil()
	{
		Log.d("MainActivity", "initUtil()");
		Utility.init(MainActivity.this);
	}
	
	private void initDisplay()
	{
		Log.d("MainActivity", "initDisplay()");
		Display.init(MainActivity.this);
		RelativeLayout mainLayout = (RelativeLayout)findViewById(R.layout.activity_main);
		mainLayout.addView(Display.getGLSurfaceView());
	}

}
