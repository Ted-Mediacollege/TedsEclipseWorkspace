package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

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
		
		initOpenGL();
		
		loadalltextures();
		
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
	
	public void loadalltextures()
	{
		int i = load("test");
		System.out.println(i);
	}
	
	public void start()
	{
		while(!Display.isCloseRequested())
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT); 
			
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 1);
			GL11.glColor3f(1, 1, 1);
			
			GL11.glPushMatrix();
				GL11.glBegin(GL11.GL_QUADS);
				
				GL11.glTexCoord2f(0.00f, 1.00f); GL11.glVertex2f(0, 0);
				GL11.glTexCoord2f(1.00f, 1.00f); GL11.glVertex2f(160, 0);
				GL11.glTexCoord2f(1.00f, 0.00f); GL11.glVertex2f(160, 160);
				GL11.glTexCoord2f(0.00f, 0.00f); GL11.glVertex2f(0, 160);
				
				GL11.glEnd();
			GL11.glPopMatrix();
			
			Display.update();
			Display.sync(60);
		}
	}
	
	public static void main(String[] args) 
	{
		new LwjglTest1();
	}
	
	public static int load(String file) 
	{
		BufferedImage bufferedimage = null;

		try 
		{
			System.out.println("LOADING...");
			bufferedimage = ImageIO.read(new File("res/" + file + ".png"));
		} 
		catch(IOException e) 
		{ 
			System.err.println("Cannot load texture");
		}

		return loadTexture(bufferedimage);
	}
	
	public static int loadTexture(BufferedImage image) 
	{
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

		for(int y = 0; y < image.getHeight(); y++) 
		{
			for(int x = 0; x < image.getWidth(); x++) 
			{
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				buffer.put((byte) ((pixel >> 24) & 0xFF));
			}
		}

		buffer.flip();

		int textureID = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		System.out.println("SAVING...");

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, image.getWidth(), image.getHeight(), 0 , GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

		return textureID;
	}
}
