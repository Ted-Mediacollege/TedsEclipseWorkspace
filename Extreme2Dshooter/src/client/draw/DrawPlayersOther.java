package client.draw;

import java.awt.Color;
import java.awt.Graphics;

import client.network.Network;
import client.world.World;

public class DrawPlayersOther 
{
	public DrawPlayersOther()
	{
		
	}
	
	public void paint(Graphics g)
	{
		for(int a = 0; a < World.players.size(); a++)
		{
			g.setColor(new Color(255, 255, 0));
			g.drawRect((int) Math.floor(World.players.get(a).posX - 12), (int) Math.floor(World.players.get(a).posY - 12), 24, 24);
			
			g.setColor(new Color(0, 255, 0));
			g.fillRect((int) Math.floor(World.players.get(a).posX) - 15, (int) Math.floor(World.players.get(a).posY) - 25, (int) Math.floor(World.players.get(a).health * 1.5F), 4);
			g.setColor(new Color(255, 0, 0));
			g.fillRect((int) Math.floor(World.players.get(a).posX) - 15 + (int) Math.floor(World.players.get(a).health * 1.5F), (int) Math.floor(World.players.get(a).posY) - 25, 30 - (int) Math.floor(World.players.get(a).health * 1.5F), 4);
		}
	}
}
