package client.draw;

import java.awt.Color;
import java.awt.Graphics;

import client.world.World;

public class DrawTerrain 
{
	public void paint(Graphics g)
	{
		for(int i = 0; i < 300; i++)
		{
			for(int j = 0; j < 200; j++)
			{
				if(World.terrain[i][j] < -0.1F)
				{
					g.setColor(new Color(50,60,254 - (int) Math.floor(-World.terrain[i][j] * 200)));
					g.fillRect(i * 5, j * 5, 5, 5);
				}
				else if(World.terrain[i][j] < -0.05F)
				{
					g.setColor(new Color(194, 178, 128));
					g.fillRect(i * 5, j * 5, 5, 5);
				}
				else if(World.terrain[i][j] < 0.25F)
				{
					g.setColor(new Color(0,(int) Math.floor(World.terrain[i][j] * 100) + 150,0));
					g.fillRect(i * 5, j * 5, 5, 5);
				}
				else
				{
					g.setColor(new Color((int) Math.floor(World.terrain[i][j] * 100) + 150,(int) Math.floor(World.terrain[i][j] * 100) + 150,(int) Math.floor(World.terrain[i][j] * 100) + 150));
					g.fillRect(i * 5, j * 5, 5, 5);
				}
			}
		}
	}
}