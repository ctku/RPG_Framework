package com.kw.rpg.engine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class Renderer implements android.opengl.GLSurfaceView.Renderer
{
	//For frame rate calculation
	private int frames = 0;
	private long lastTime = System.nanoTime();
	private long totalTime = 0;
	
	private static RendererCallback callback;
	public interface RendererCallback {
		void func(GL10 gl);
	}
	public static void setCallback(RendererCallback cb)
	{
		callback = cb;
	}

	@Override
	public void onDrawFrame(GL10 gl) 
	{
		long now = System.nanoTime();
		long passed = now - lastTime;
		lastTime = now;
		totalTime += passed;
		
		if (totalTime >= 1000000000)
		{
			//System.out.println(frames);
			totalTime = 0;
			frames = 0;
		}
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		callback.func(gl);
		
		frames++;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
		Log.d("Renderer", "onSurfaceChanged()");
		initGL(gl,width,height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{
		Log.d("Renderer", "onSurfaceCreated()");
		initGL(gl,Utility.getScreenWidth(),Utility.getScreenHeight());
	}
	
	private void initGL(GL10 gl, int w, int h)
	{
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0,w,0,h,-1,1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		
		gl.glDisable(GL10.GL_DEPTH_TEST);
		
		gl.glClearColor(0,0,0,0);
	}
}
