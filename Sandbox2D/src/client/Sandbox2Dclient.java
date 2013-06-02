package client;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import client.draw.Draw;
import client.world.World;

public class Sandbox2Dclient 
{
	public boolean[] keyinput = new boolean[4];
	public int[] mousepos = new int[2];
	
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
		
		start();
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
			input();
			
			world.tick(keyinput);
			draw.drawScreen();
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) 
	{
		new Sandbox2Dclient();
	}
	
	public void input() 
	{
		if (Mouse.isButtonDown(0)) 
		{
			mousepos[0] = Mouse.getX();
			mousepos[1] = Mouse.getY();
		}
		 
		while (Keyboard.next()) 
		{
			if (Keyboard.getEventKeyState()) 
			{
				if (Keyboard.getEventKey() == Keyboard.KEY_W) 
				{
					keyinput[0] = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_A) 
				{
					keyinput[1] = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) 
				{
					keyinput[2] = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) 
				{
					keyinput[3] = true;
				}
			} 
			else 
			{
				if (Keyboard.getEventKey() == Keyboard.KEY_W) 
				{
					keyinput[0] = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_A) 
				{
					keyinput[1] = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) 
				{
					keyinput[2] = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) 
				{
					keyinput[3] = false;
				}
			}
		}
	}
}
