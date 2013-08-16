package com.kw.rpg.engine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class Renderer implements android.opengl.GLSurfaceView.Renderer
{
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
		//Log.d("Renderer", "onDrawFrame()");
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		callback.func(gl);
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
