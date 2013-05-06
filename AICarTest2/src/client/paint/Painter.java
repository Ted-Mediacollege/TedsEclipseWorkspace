package client.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import client.util.Coords;
import client.world.World;

public class Painter extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public Painter()
	{
		
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 1024, 768);
		
		g.setColor(new Color(255, 0, 0));
		g.drawOval( 
			(int) Math.floor(World.vehicle[0]) - 5, 
			(int) Math.floor(World.vehicle[1]) - 5,
			10,
			10
		);
		g.drawLine(
			(int) Math.floor(World.vehicle[0]),
			(int) Math.floor(World.vehicle[1]),
			(int) Math.floor(World.vehicle[0]) + ((int) (World.vehicle[2] * 10D) * (int) Math.cos(World.vehicle[3] * Math.PI / 180.0)), 
			(int) Math.floor(World.vehicle[1]) + ((int) (World.vehicle[2] * 10D) * (int) Math.sin(World.vehicle[3] * Math.PI / 180.0))
		);
		
        Toolkit.getDefaultToolkit().sync();
        g.dispose();	
	}
	
	public void run()
	{
		repaint();
	}
}
