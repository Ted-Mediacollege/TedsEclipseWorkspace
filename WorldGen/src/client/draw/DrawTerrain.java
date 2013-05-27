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
				int t = (int) Math.floor(-World.terrain[i][j]) + 155;
				if(t < 0) { t = 0; }
				if(t > 254) { t = 254; }

				g.setColor(new Color(t,t,t));
				g.fillRect(i * 5, j * 5, 5, 5);
			}
		}
	}
}
