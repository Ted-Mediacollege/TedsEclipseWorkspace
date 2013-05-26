package client;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import client.draw.Draw;
import client.world.World;

public class Sandbox2Dclient 
{
	public Draw draw;
	public World world;
	
	public Sandbox2Dclient()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(1024, 768));
			Display.setTitle("Sandbox2D - client");
			Display.create();
		}
		catch(LWJGLException e)
		{
			System.out.println("Cannot create screen!");
		}
		
		initOpenGL();
		draw = new Draw();
		world = new World();
		start();
	}
	
	public void initOpenGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public void start()
	{
		while(!Display.isCloseRequested())
		{
			Display.update();
			Display.sync(60);
		}
	}
	
	public static void main(String[] args) 
	{
		new Sandbox2Dclient();
	}
}
