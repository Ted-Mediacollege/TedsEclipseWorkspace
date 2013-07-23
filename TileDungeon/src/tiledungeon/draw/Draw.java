package tiledungeon.draw;

import org.lwjgl.opengl.GL11;

import tiledungeon.tiles.Tile;
import tiledungeon.world.World;

public class Draw 
{
	public static int[] tiletextures = new int[1];
	
	public Draw()
	{
		tiletextures[0] = DrawLoader.load("textures/tiles/Tiles01");
	}
	
	public void drawScreen()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		for(int x = 0; x < World.currentlevel.sizeX; x++)
		{
			for(int y = 0; y < World.currentlevel.sizeY; y++)
			{
				int tx = Tile.tileList[World.currentlevel.tiles[x][y]].textureID;
				int ty = 0;
				
				while(tx > 19)
				{
					tx -= 20;
					ty += 1;
				}
				
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, tiletextures[0]);
				GL11.glColor3f(1F, 1F, 1F);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				
				GL11.glPushMatrix();
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0.05f * tx, 0.5f * ty + 0.5f); 
					GL11.glVertex2f(
						World.camera.camX
						+ (x * 40),
						
						World.camera.camY
						- (y * 40)
					);
					GL11.glTexCoord2f(0.05f * tx + 0.05f, 0.5f * ty + 0.5f); 
					GL11.glVertex2f(
						World.camera.camX
						+ (x * 40)
						+ 40,
						
						World.camera.camY
						- (y * 40)
						
					);
					GL11.glTexCoord2f(0.05f * tx + 0.05f, 0.5f * ty); 
					GL11.glVertex2f(
						World.camera.camX
						+ (x * 40)
						+ 40,
						
						World.camera.camY
						- (y * 40)
						- 80
					);
					GL11.glTexCoord2f(0.05f * tx, 0.5f * ty); 
					GL11.glVertex2f(
						World.camera.camX
						+ (x * 40),
						
						World.camera.camY
						- (y * 40)
						- 80
					);
					GL11.glEnd();
				GL11.glPopMatrix();
			}
		}
	}
}
