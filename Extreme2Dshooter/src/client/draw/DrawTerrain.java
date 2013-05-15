package client.draw;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import client.world.World;

public class DrawTerrain 
{
	private BufferedImage terrainTextures;
	
	public DrawTerrain()
	{
		try
		{
			terrainTextures = ImageIO.read(new File("textures/terrain.png"));
		}
		catch(Exception e)
		{
			System.out.println("Cannot load textures");
		}
	}
	
	public void paint(Graphics g)
	{
		for(int i = 0; i < World.level.length; i++)
		{
			for(int j = 0; j < World.level[0].length; j++)
			{
				int tx = World.level[i][j];
				int ty = 0;
				
				while(tx > 9)
				{
					tx -= 10;
					ty += 1;
				}
				
				g.drawImage(
					terrainTextures, 
					i * 24, 
					j * 24, 
					i * 24 + 24, 
					j * 24 + 24, 
					tx * 24, 
					ty * 24, 
					tx * 24 + 24, 
					ty * 24 + 24, 
					null);
			}
		}
	}
}
