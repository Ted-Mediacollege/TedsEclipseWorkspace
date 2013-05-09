package client.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

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
		
		for(int i = 0; i < World.paths.size(); i++)
		{
			if(i == 0) { g.setColor(new Color(255, 255, 0)); }
			if(i == 1) { g.setColor(new Color(0, 255, 0)); }
			if(i == 2) { g.setColor(new Color(255, 150, 0)); }
			
			for(int j = 0; j < World.paths.get(i).path.length; j++)
			{
				g.drawOval( 
					World.paths.get(i).path[j][0] - 5, 
					World.paths.get(i).path[j][1] - 5,
					10,
					10
				);
				
				if(j != 0)
				{
					g.drawLine(
						World.paths.get(i).path[j][0],
						World.paths.get(i).path[j][1],
						World.paths.get(i).path[j - 1][0],
						World.paths.get(i).path[j - 1][1]
					);
				}
			}
		}
		
		for(int v = 0; v < World.vehicles.size(); v++)
		{
			if(World.vehicles.get(v).path == -1) { g.setColor(new Color(255, 255, 255)); } else { g.setColor(new Color(255, 0, 0)); }
			g.drawOval( 
				(int) Math.floor(World.vehicles.get(v).posX) - 5, 
				(int) Math.floor(World.vehicles.get(v).posY) - 5,
				10,
				10
			);
			
			g.drawPolyline(
				new int[]{(int) Math.floor(World.vehicles.get(v).hitbox[0][0]), (int) Math.floor(World.vehicles.get(v).hitbox[1][0]), (int) Math.floor(World.vehicles.get(v).hitbox[3][0]), (int) Math.floor(World.vehicles.get(v).hitbox[2][0]), (int) Math.floor(World.vehicles.get(v).hitbox[0][0])}, 
				new int[]{(int) Math.floor(World.vehicles.get(v).hitbox[0][1]), (int) Math.floor(World.vehicles.get(v).hitbox[1][1]), (int) Math.floor(World.vehicles.get(v).hitbox[3][1]), (int) Math.floor(World.vehicles.get(v).hitbox[2][1]), (int) Math.floor(World.vehicles.get(v).hitbox[0][1])}, 
				5
			);
		}

        Toolkit.getDefaultToolkit().sync();
        g.dispose();	
	}
	
	public void run()
	{
		repaint();
	}
}
