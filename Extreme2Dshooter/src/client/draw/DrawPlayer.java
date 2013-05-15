package client.draw;

import java.awt.Color;
import java.awt.Graphics;

import client.world.World;

public class DrawPlayer 
{
	public DrawPlayer()
	{
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(255, 0, 0));
		g.drawRect((int) Math.floor(World.player.posX - 12), (int) Math.floor(World.player.posY - 12), 24, 24);
		g.setColor(new Color(0, 255, 0));
		g.fillRect((int) Math.floor(World.player.posX) - 15, (int) Math.floor(World.player.posY) - 25, (int) Math.floor(World.player.health * 1.5F), 4);
		g.setColor(new Color(255, 0, 0));
		g.fillRect((int) Math.floor(World.player.posX) - 15 + (int) Math.floor(World.player.health * 1.5F), (int) Math.floor(World.player.posY) - 25, 30 - (int) Math.floor(World.player.health * 1.5F), 4);
	}
}
