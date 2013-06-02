package client.draw;

import org.lwjgl.opengl.GL11;

public class Draw 
{
	public DrawTerrain drawterrain = new DrawTerrain();
	
	public static int terrainTextureID;
	
	public Draw()
	{
		terrainTextureID = DrawLoader.load("terrain");
	}
	
	public void drawScreen()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
		drawterrain.draw();
	}
} 
