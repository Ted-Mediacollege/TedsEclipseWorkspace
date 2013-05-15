package client.draw;

import java.awt.Color;
import java.awt.Graphics;

import client.world.World;

public class DrawProjectiles 
{
	public DrawProjectiles()
	{
		
	}

	public void paint(Graphics g)
	{
		for(int b = 0; b < World.projectiles.size(); b++)
		{
			if(World.projectiles.get(b).explodable)
			{
				g.setColor(new Color(255, 0, 0));
			}
			else
			{
				g.setColor(new Color(0, 0, 255));
			}
			g.drawOval((int) Math.floor(World.projectiles.get(b).posX) - 5, (int) Math.floor(World.projectiles.get(b).posY) - 5, 10, 10);
		}
	}
}
