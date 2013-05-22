package client;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class LwjglTest1 
{
	public LwjglTest1()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(1024, 768));
			Display.setTitle("LWJGL test 01");
			Display.create();
		}
		catch(LWJGLException e)
		{
			System.out.println("Cannot create screen!");
		}
		
		tick();
	}
	
	public void tick()
	{
		while(!Display.isCloseRequested())
		{
			Display.update();
			Display.sync(60);
		}
	}
	
	public static void main(String[] args) 
	{
		new LwjglTest1();
	}
}
