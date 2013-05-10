package client.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.MainClass;
import client.util.Coords;
import client.world.World;

public class Painter extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private BufferedImage textureTerrain;
	private BufferedImage textureCars;
	
	public boolean debug = true;

	public Painter()
	{
		try
		{
			textureTerrain = ImageIO.read(new File("textures/terrain.png"));
			textureCars = ImageIO.read(new File("textures/cars.png"));
		}
		catch(Exception e)
		{
			System.out.println("[ERROR] failed to load all textures!");
		} 

		try
		{
			textureTerrain = ImageIO.read(MainClass.class.getResource("/textures/terrain.png"));
			textureCars = ImageIO.read(MainClass.class.getResource("/textures/cars.png"));
		}
		catch(Exception e)
		{
			System.out.println("[ERROR] failed to load all textures!");
		} 
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 1024, 768);
		
		for(int x = 0; x < 40; x++)
		{
			for(int y = 0; y < 40; y++)
			{
				g.drawImage(
					textureTerrain, 
					x * 26,
					y * 26, 
					x * 26 + 26, 
					y * 26 + 26, 
					x * 27, 
					y * 27, 
					x * 27 + 26, 
					y * 27 + 26, 
					null
				);
			}
		}
		
		for(int v = 0; v < World.vehicles.size(); v++)
		{
	        AffineTransform at = new AffineTransform();
	        at.translate((int) Math.floor(World.vehicles.get(v).posX) - 13, (int) Math.floor(World.vehicles.get(v).posY) - 13);
	        at.rotate((int) Math.floor(World.vehicles.get(v).posR) * 0.0174532925, 13, 13);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.drawImage(textureCars, at, null);
			
			if(debug)
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
				
				g.setColor(new Color(155, 155, 255));
				g.drawOval( 
					(int) Math.floor(World.vehicles.get(v).targetX) - 3, 
					(int) Math.floor(World.vehicles.get(v).targetY) - 3,
					6,
					6
				);				
				
				g.setColor(new Color(255, 255, 255));
				g.drawString(World.vehicles.get(v).cSpeed + "", (int) Math.floor(World.vehicles.get(v).posX) + 5, (int) Math.floor(World.vehicles.get(v).posY) + 15);
			}
		}
        
		if(debug)
		{
			for(int i = 0; i < World.paths.size(); i++)
			{
				g.setColor(new Color(255, 255, 0));
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
			
			g.setColor(new Color(0, 0, 0));
			for(int r = 0; r < World.removers.size(); r++)
			{
				g.drawRect(
					World.removers.get(r).x1,
					World.removers.get(r).y1,
					World.removers.get(r).x2 - World.removers.get(r).x1,
					World.removers.get(r).y2 - World.removers.get(r).y1
				);
			}
	
			for(int t = 0; t < World.trafficlights.size(); t++)
			{
				if(World.trafficlights.get(t).closed == true) 
				{ 
					g.setColor(new Color(255, 0, 0)); 
				} else {
					g.setColor(new Color(0, 255, 0));
				}
				
				g.drawRect(
					World.trafficlights.get(t).x1,
					World.trafficlights.get(t).y1,
					World.trafficlights.get(t).x2 - World.trafficlights.get(t).x1,
					World.trafficlights.get(t).y2 - World.trafficlights.get(t).y1
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
