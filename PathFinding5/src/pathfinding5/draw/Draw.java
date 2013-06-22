package pathfinding5.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import pathfinding5.util.Coords;
import pathfinding5.world.World;

public class Draw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	public Draw()
	{
		
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);

		for(int i = 0; i < World.nodes.size(); i++)
		{			
			g.setColor(new Color(200,200,200));
			for(int j = 0; j < World.nodes.get(i).connections.size(); j++)
			{
				g.drawLine(
					World.nodes.get(i).posX, 
					World.nodes.get(i).posY, 
					World.nodes.get(World.nodes.get(i).connections.get(j)).posX, 
					World.nodes.get(World.nodes.get(i).connections.get(j)).posY
				);
				
			}
		}
			
		for(int i = 0; i < World.nodes.size(); i++)
		{		
			if(i == World.startNode || i == World.endNode)
			{
				g.setColor(new Color(0,0,255));
			}
			else if(World.nodes.get(i).visited)
			{
				g.setColor(new Color(255,0,0));
			}
			else
			{
				g.setColor(new Color(0,0,0));
			}
			
			g.drawOval(World.nodes.get(i).posX - 5, World.nodes.get(i).posY - 5, 10, 10);
		}
		
		g.setColor(new Color(255,0,0));
		for(int k = 0; k < World.paths.size(); k++)
		{
			if(!World.paths.get(k).dead)
			{
				double[] p = Coords.getNextXY(
					World.nodes.get(World.paths.get(k).nodeID).posX, 
					World.nodes.get(World.paths.get(k).nodeID).posY, 
					Coords.getDegreeFromPoint(World.nodes.get(World.paths.get(k).nodeID).posX, World.nodes.get(World.paths.get(k).nodeID).posY, World.nodes.get(World.paths.get(k).target).posX, World.nodes.get(World.paths.get(k).target).posY), 
					World.paths.get(k).travel
				);
				
				g.drawLine(
					World.nodes.get(World.paths.get(k).nodeID).posX,
					World.nodes.get(World.paths.get(k).nodeID).posY,
					(int) Math.floor(p[0]),
					(int) Math.floor(p[1])
				);
			}
		}
		
		if(World.calculatedPath != null)
		{
			g.setColor(new Color(0,0,255));
			for(int l = 1; l < World.calculatedPath.length; l++)
			{
				g.drawLine(
					World.nodes.get(World.calculatedPath[l - 1]).posX, 
					World.nodes.get(World.calculatedPath[l - 1]).posY, 
					World.nodes.get(World.calculatedPath[l]).posX, 
					World.nodes.get(World.calculatedPath[l]).posY
				);
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
