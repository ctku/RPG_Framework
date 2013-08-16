package com.kw.rpg.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class Utility {

	private static Context ctx;
	private static android.view.Display disp;
	
	public static void init(Context context)
	{
		ctx = context;
		disp = ((WindowManager)ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	}
	
	public static void getScreenDim(Point outSize) 
	{
		boolean foundSize = false;
		Point tmpSize = new Point();
	    try {
			// test for new method to trigger exception
			Method newGetSize = Display.class.getMethod("getSize", new Class[]{ Point.class });
			// no exception, so new method is available, just use it
			newGetSize.invoke(disp, tmpSize);
			foundSize = true;
		} 
	    catch (NoSuchMethodException ex) {}
	    catch (InvocationTargetException e)  {}
	    catch (IllegalArgumentException e) {}
	    catch (IllegalAccessException e) {}
		if (foundSize) {
        	outSize.x = tmpSize.x;
        	outSize.y = tmpSize.y;
		} else {
        	outSize.x = disp.getWidth();
        	outSize.y = disp.getHeight();
		}
	}
	
	public static int getScreenWidth() 
	{
		Point outSize = new Point();
		Utility.getScreenDim(outSize);
		return outSize.x;
	}
	
	public static int getScreenHeight() 
	{
		Point outSize = new Point();
		Utility.getScreenDim(outSize);
		return outSize.y;
   }
}
