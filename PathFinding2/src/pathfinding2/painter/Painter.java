package pathfinding2.painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import pathfinding2.point.Point;
import pathfinding2.world.World;

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

		for(int i = 0; i < World.points.length; i++)
		{		
			if(World.points[i] == null)
			{
				break;
			}
			else
			{
				Point p = World.points[i];
			
				g.setColor(new Color(255, 255, 255));
				g.drawString(p.name, (int) Math.floor(p.posX) + 15, (int) Math.floor(p.posY) + 15);
				g.drawOval((int) Math.floor(p.posX) - 5, (int) Math.floor(p.posY) - 5, 10, 10);
				
				for(int j = 0; j < p.friends.length; j++)
				{
					if(i > j)
					{
						g.drawLine((int) Math.floor(p.posX), (int) Math.floor(p.posY), (int) Math.floor(World.points[p.friends[j]].posX), (int) Math.floor(World.points[p.friends[j]].posY));
					}
				}
			}
		}

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}
	
	public void run()
	{
		repaint();
	}
}
