package com.kw.rpg.engine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

public class Display 
{
	private static GLSurfaceView glSurfaceView;
	private static com.kw.rpg.engine.Renderer renderer;

	public static void init(Context contex)
	{
		Log.d("Display", "init()");
		renderer = new com.kw.rpg.engine.Renderer();
		glSurfaceView = new GLSurfaceView(contex);
		glSurfaceView.setRenderer(renderer);
	}
	
	public static GLSurfaceView getGLSurfaceView()
	{
		return glSurfaceView;
	}
	
	
}
