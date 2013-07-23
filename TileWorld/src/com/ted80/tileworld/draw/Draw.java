package com.ted80.tileworld.draw;

import org.lwjgl.opengl.GL11;

import com.ted80.tileworld.tile.Tile;
import com.ted80.tileworld.world.World;

public class Draw
{
	public static int[] tiletoptextures = new int[1];
	public static int[] tilesidetextures = new int[1];
	
	public Draw()
	{
		tiletoptextures[0] = DrawLoader.load("tile/tiles-top01");
		tilesidetextures[0] = DrawLoader.load("tile/tiles-side01");
	}

	public void drawScreen() 
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		for(int c = 0; c < World.chunks.size(); c++)
		{
			for(int x = 0; x < 10; x++)
			{
				for(int y = 0; y < 10; y++)
				{
					for(int z = 39; z > -1; z--)
					{
						if(World.chunks.get(c).tiles[x][y][z] != 0)
						{
							int tx = Tile.tileList[World.chunks.get(c).tiles[x][y][z]].textureID;
							int ty = 0;
							
							while(tx > 9)
							{
								tx -= 10;
								ty += 1;
							}
							
							GL11.glBindTexture(GL11.GL_TEXTURE_2D, tiletoptextures[0]);
							GL11.glColor3f(0.6F + (z * 0.0125F), 0.6F + (z * 0.0125F), 0.6F + (z * 0.0125F));
							GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
							
							GL11.glPushMatrix();
								GL11.glBegin(GL11.GL_QUADS);
								GL11.glTexCoord2f(0.10f * tx, 0.20f * ty + 0.20f); 
								GL11.glVertex2f(World.camX + (World.chunks.get(c).posX * (10 * 20)) + (x * 20), World.camY - (World.chunks.get(c).posY * (10 * 20)) - (y * 20) + (z * 5));
								GL11.glTexCoord2f(0.10f * tx + 0.10f, 0.20f * ty + 0.20f); 
								GL11.glVertex2f(World.camX + (World.chunks.get(c).posX * (10 * 20)) + (x * 20) + 20, World.camY - (World.chunks.get(c).posY * (10 * 20)) - (y * 20) + (z * 5));
								GL11.glTexCoord2f(0.10f * tx + 0.10f, 0.20f * ty); 
								GL11.glVertex2f(World.camX + (World.chunks.get(c).posX * (10 * 20)) + (x * 20) + 20, World.camY - (World.chunks.get(c).posY * (10 * 20)) - (y * 20) + (z * 5) + 20);
								GL11.glTexCoord2f(0.10f * tx, 0.20f * ty); 
								GL11.glVertex2f(World.camX + (World.chunks.get(c).posX * (10 * 20)) + (x * 20), World.camY - (World.chunks.get(c).posY * (10 * 20)) - (y * 20) + (z * 5) + 20);
								GL11.glEnd();
							GL11.glPopMatrix();
							
							int side = World.getHighestTile(x + (World.chunks.get(c).posX * 10), y + (World.chunks.get(c).posY * 10) + 1);
							if(side < z && side > -1)
							{
								for(int n = z; n > side; n--)
								{
									GL11.glBindTexture(GL11.GL_TEXTURE_2D, tilesidetextures[0]);
									GL11.glColor3f(0.8F + (z * 0.025F), 0.8F + (z * 0.025F), 0.8F + (z * 0.025F));
									GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
									
									int stx = Tile.tileList[World.chunks.get(c).tiles[x][y][n]].textureID;
									int sty = 0;
									
									while(stx > 9)
									{
										stx -= 10;
										sty += 1;
									}
									
									GL11.glPushMatrix();
										GL11.glBegin(GL11.GL_QUADS);
										GL11.glTexCoord2f(0.10f * stx, 0.20f * sty + 0.20f); 
										GL11.glVertex2f(
											World.camX 
											+ (World.chunks.get(c).posX * (10 * 20)) 
											+ (x * 20)
											
											,World.camY 
											- (World.chunks.get(c).posY * (10 * 20)) 
											- (y * 20) 
											+ (n * 5)
											- 5
										);
										GL11.glTexCoord2f(0.10f * stx + 0.10f, 0.20f * sty + 0.20f); 
										GL11.glVertex2f(
											World.camX 
											+ (World.chunks.get(c).posX * (10 * 20)) 
											+ (x * 20) 
											+ 20,
											
											World.camY 
											- (World.chunks.get(c).posY * (10 * 20)) 
											- (y * 20) 
											+ (n * 5)
											- 5
										);
										GL11.glTexCoord2f(0.10f * stx + 0.10f, 0.20f * sty); 
										GL11.glVertex2f(
											World.camX 
											+ (World.chunks.get(c).posX * (10 * 20)) 
											+ (x * 20) 
											+ 20, 
											
											World.camY 
											- (World.chunks.get(c).posY * (10 * 20)) 
											- (y * 20) 
											+ (n * 5) 
										);
										GL11.glTexCoord2f(0.10f * stx, 0.20f * sty); 
										GL11.glVertex2f(
											World.camX 
											+ (World.chunks.get(c).posX * (10 * 20)) 
											+ (x * 20),
											
											World.camY 
											- (World.chunks.get(c).posY * (10 * 20)) 
											- (y * 20) 
											+ (n * 5) 
										);
										GL11.glEnd();
									GL11.glPopMatrix();
								}
							}
							
							break;
						}
					}
				}
			}
		}
	}
}
