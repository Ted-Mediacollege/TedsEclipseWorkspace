package client.draw;

import org.lwjgl.opengl.GL11;

import client.ground.Terrain;
import client.world.World;

public class DrawTerrain 
{
	public void draw()
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Draw.terrainTextureID);
		GL11.glColor3f(1, 1, 1);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		
		for(int c = 0; c < World.chunklist.size(); c++)
		{
			for(int x = 0; x < World.chunklist.get(c).terrain.length; x++)
			{
				for(int y = 0; y < World.chunklist.get(c).terrain[0].length; y++)
				{
					int tx = Terrain.terrainList[World.chunklist.get(c).terrain[x][y]].terrainID;
					int ty = 0;
					
					while(tx > 9)
					{
						tx -= 10;
						ty += 1;
					}
					
					GL11.glPushMatrix();
						GL11.glBegin(GL11.GL_QUADS);
						GL11.glTexCoord2f(0.10f * tx, 0.10f * ty + 0.10f); 
						GL11.glVertex2f(World.camX + (World.chunklist.get(c).chunkX * (10 * 48)) + (x * 48), World.camY - (World.chunklist.get(c).chunkY * (10 * 48)) - (y * 48));
						GL11.glTexCoord2f(0.10f * tx + 0.10f, 0.10f * ty + 0.10f); 
						GL11.glVertex2f(World.camX + (World.chunklist.get(c).chunkX * (10 * 48)) + (x * 48) + 48, World.camY - (World.chunklist.get(c).chunkY * (10 * 48)) - (y * 48));
						GL11.glTexCoord2f(0.10f * tx + 0.10f, 0.10f * ty); 
						GL11.glVertex2f(World.camX + (World.chunklist.get(c).chunkX * (10 * 48)) + (x * 48) + 48, World.camY - (World.chunklist.get(c).chunkY * (10 * 48)) - (y * 48) + 48);
						GL11.glTexCoord2f(0.10f * tx, 0.10f * ty); 
						GL11.glVertex2f(World.camX + (World.chunklist.get(c).chunkX * (10 * 48)) + (x * 48), World.camY - (World.chunklist.get(c).chunkY * (10 * 48)) - (y * 48) + 48);
						GL11.glEnd();
					GL11.glPopMatrix();
				}
			}
		}
	}
}
