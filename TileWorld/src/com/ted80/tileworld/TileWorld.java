package com.ted80.tileworld;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.ted80.tileworld.draw.Draw;
import com.ted80.tileworld.world.World;

public class TileWorld 
{
	public World world;
	public Draw draw;
	
	public TileWorld()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(1024, 768));
			Display.setTitle("TileWorld");
			Display.create();
		}
		catch(LWJGLException e) { }
		
		initOpenGL();
		
		start();
	}
	
	public static void main(String[] args) 
	{
		new TileWorld();
	}
	
	public void initOpenGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1024, 0, 768, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public void start()
	{
		draw = new Draw();
		world = new World();
		
		while(!Display.isCloseRequested())
		{
			draw.drawScreen();
			world.tick();
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
}
