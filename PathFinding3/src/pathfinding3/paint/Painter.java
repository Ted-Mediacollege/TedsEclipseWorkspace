package pathfinding3.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import pathfinding3.world.World;

public class Painter extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public Painter()
	{
		
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);

		for(int k = 0; k < World.map.length; k++)
		{
			for(int l = 0; l < World.map[0].length; l++)
			{
				if(World.map[k][l] == 1)
				{
					g.setColor(new Color(100, 100, 100));
				}
				else
				{
					g.setColor(new Color(175, 175, 175));
				}
				g.fillRect(k * 20, l * 20, 20, 20);
			}
		}
		
		if(World.calculating)
		{
			for(int i = 0; i < World.used.length; i++)
			{
				for(int j = 0; j < World.used[0].length; j++)
				{
					if(World.used[i][j] == true)
					{
						g.setColor(new Color(255, 0, 0, 100));
						g.drawOval(i * 20, j * 20, 20, 20);
					}
				}
			}
			
			for(int k = 0; k < World.paths.size(); k++)
			{
				g.setColor(new Color(0, 0, 0));
				g.drawOval(World.paths.get(k).coordX * 20, World.paths.get(k).coordY * 20, 20, 20);
			}
		}
		
		if(World.calculated)
		{
			for(int i = 0; i < World.path.size(); i++)
			{
				g.setColor(new Color(0, 0, 0, 255));
				g.fillRect(World.path.get(i)[0] * 20, World.path.get(i)[1] * 20, 20, 20);
			}
		}
		
		g.setColor(new Color(0, 0, 255));
		g.fillRect(World.start[0] * 20, World.start[1] * 20, 20, 20);
		g.fillRect(World.target[0] * 20, World.target[1] * 20, 20, 20);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}
	
	public void run()
	{
		repaint();
	}
}
