package client.draw;

import java.awt.Color;
import java.awt.Graphics;

import client.Extreme2Dshooter;

public class DrawDebug 
{
	public DrawDebug()
	{
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(0, 0, 0));
		g.drawString("FPS: " + Extreme2Dshooter.fps , 10, 15);
	}
}
