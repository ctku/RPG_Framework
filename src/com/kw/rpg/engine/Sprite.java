package com.kw.rpg.engine;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Sprite {

	private float r;
	private float g;
	private float b;
	
	private float sx;
	private float sy;

	private FloatBuffer verBuffer;  
	private float vertices[] = { 
		0f, 0f,
		0f, 1f,
		1f, 1f,
		1f, 0f
	};
	
	public Sprite(float r, float g, float b, float sx, float sy)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.sx = sx;
		this.sy = sy;
		
        for (int i = 0; i < 8; i += 2) {  
            vertices[i] *= sx;  
            vertices[i+1] *= sy;  
        }     
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertices.length * 4);  
        qbb.order(ByteOrder.nativeOrder());  
        verBuffer = qbb.asFloatBuffer();  
        verBuffer.put(vertices);  
        verBuffer.position(0);   
	}	
	
	/*
	private float[] vertices = new float[720];  
	private FloatBuffer verBuffer;  

	public Sprite(float r, float g, float b, float sx, float sy)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.sx = sx;
		this.sy = sy;
		
        for (int i = 0; i < 720; i += 2) {  
            vertices[i]   =  (float) (Math.cos((float) (Math.PI * i / 180.0)) * sx + sx / 2.0);  
            vertices[i+1] =  (float) (Math.sin((float) (Math.PI * i / 180.0)) * sy + sy / 2.0);  
        }     
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertices.length * 4);  
        qbb.order(ByteOrder.nativeOrder());  
        verBuffer = qbb.asFloatBuffer();  
        verBuffer.put(vertices);  
        verBuffer.position(0);   
	}
	*/

	public void render(GL10 gl)
	{
		gl.glColor4f(r, g, b, 1);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);  
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, verBuffer);          
        //gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 360);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);  
	}
	
	public float getSX()
	{
		return sx;
	}
	
	public float getSY()
	{
		return sy;
	}
	
	public void setSX(float sx)
	{
		this.sx = sx;
	}
	
	public void setSY(float sy)
	{
		this.sy = sy;
	}
}
