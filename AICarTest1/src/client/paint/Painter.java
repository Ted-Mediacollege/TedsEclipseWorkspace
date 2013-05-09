package client.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.MainClass;
import client.world.World;

public class Painter extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private BufferedImage textureTiles;
	private BufferedImage textureCars;
	
	public boolean debug = true;

	public Painter()
	{
		try
		{
			textureCars = ImageIO.read(new File("textures/cars.png"));
			textureTiles = ImageIO.read(new File("textures/tiles2.png"));
		}
		catch(Exception e)
		{
			System.out.println("[ERROR] failed to load all textures!");
		} 

		try
		{
			textureCars = ImageIO.read(MainClass.class.getResource("/textures/cars.png"));
			textureTiles = ImageIO.read(MainClass.class.getResource("/textures/tiles2.png"));
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
		g.fillRect(0, 0, 60*24, 40*24);
		
		for(int i = 0; i < World.Terrain[0].length; i++)
		{
			for(int j = 0; j < World.Terrain.length; j++)
			{
				int tx = World.Terrain[i][j];
				int ty = 0;
				
				for(int t = 0; t < 10; t++)
				{
					if(tx > 9) { tx-=10; ty++; }
				}
				
				g.drawImage(
					textureTiles, 
					i * 24,
					j * 24, 
					i * 24 + 24, 
					j * 24 + 24, 
					tx * 24, 
					ty * 24, 
					tx * 24 + 24, 
					ty * 24 + 24, 
					null
				);
			}
		}
		
		for(int m = 0; m < World.vehicles.size(); m++)
		{
			g.setColor(new Color(0, 0, 0));
			g.drawImage(
				textureCars, 
				(int) Math.floor(World.vehicles.get(m).posX) - 12,
				(int) Math.floor(World.vehicles.get(m).posY) - 12, 
				(int) Math.floor(World.vehicles.get(m).posX) + 12, 
				(int) Math.floor(World.vehicles.get(m).posY) + 12, 
				World.vehicles.get(m).textureX, 
				World.vehicles.get(m).textureY, 
				World.vehicles.get(m).textureX + 24, 
				World.vehicles.get(m).textureY + 24, 
				null
			);
		}
		
		for(int b = 0; b < World.trafficlights.size(); b++)
		{
			if(World.trafficlights.get(b).green != null)
			{
				int[][] green = World.trafficlights.get(b).green;
				int[][] red = World.trafficlights.get(b).red;

				if(World.trafficlights.get(b).state == true)
				{
					for(int c = 0; c < green.length; c++)
					{
						g.drawImage(
								textureTiles, 
								red[c][0],
								red[c][1], 
								red[c][0] + 24, 
								red[c][1] + 24, 
								red[c][2], 
								red[c][3], 
								red[c][2] + 24, 
								red[c][3] + 24, 
								null
							);
					}
				}
				else
				{
					for(int c = 0; c < green.length; c++)
					{
						g.drawImage(
								textureTiles, 
								green[c][0],
								green[c][1], 
								green[c][0] + 24, 
								green[c][1] + 24, 
								green[c][2], 
								green[c][3], 
								green[c][2] + 24, 
								green[c][3] + 24, 
								null
							);
					}
				}
			}
		}
	
		for(int i = 0; i < World.Terrain[0].length; i++)
		{
			for(int j = 0; j < World.Terrain.length; j++)
			{
				g.setColor(new Color(20, 0, 90, 0));
				g.fillRect(i * 24, j * 24, 24, 24);
			}
		}
		
		if(debug)
		{
			for(int r = 0; r < World.removers.size(); r++)
			{
				g.setColor(new Color(0, 0, 0));
				g.drawRect(
					World.removers.get(r).x1, 
					World.removers.get(r).y1, 
					World.removers.get(r).x2 - World.removers.get(r).x1, 
					World.removers.get(r).y2 - World.removers.get(r).y1
				);
			}

			for(int t = 0; t < World.trafficlights.size(); t++)
			{
				if(World.trafficlights.get(t).state == true) { g.setColor(new Color(255, 0, 0)); } else { g.setColor(new Color(0, 200, 0)); }
				g.drawRect(
					World.trafficlights.get(t).x1, 
					World.trafficlights.get(t).y1, 
					World.trafficlights.get(t).x2 - World.trafficlights.get(t).x1, 
					World.trafficlights.get(t).y2 - World.trafficlights.get(t).y1
				);
			}
			
			for(int m = 0; m < World.vehicles.size(); m++)
			{
				int x = (int) Math.floor(World.vehicles.get(m).posX);
				int y = (int) Math.floor(World.vehicles.get(m).posY);
				int s = World.vehicles.get(m).cSpeed;
				int[] h = World.vehicles.get(m).hitbox;
				
				g.setColor(new Color(0, 255, 0));
				g.drawRect(
					x + h[0],
					y + h[2],
					h[1] - h[0],
					h[3] - h[2]
				);
				
				g.setColor(new Color(255, 0, 0));
				if(World.vehicles.get(m).dir == 1) { g.drawLine(x, y, x, y - s); }
				if(World.vehicles.get(m).dir == 2) { g.drawLine(x, y, x + s, y); }
				if(World.vehicles.get(m).dir == 3) { g.drawLine(x, y, x, y + s); }
				if(World.vehicles.get(m).dir == 4) { g.drawLine(x, y, x - s, y); }
				if(World.vehicles.get(m).dir == 1) { g.drawOval(x - 3, y - s - 3, 6, 6); }
				if(World.vehicles.get(m).dir == 2) { g.drawOval(x + s - 3, y - 3, 6, 6); } 
				if(World.vehicles.get(m).dir == 3) { g.drawOval(x - 3, y + s - 3, 6, 6); }
				if(World.vehicles.get(m).dir == 4) { g.drawOval(x - s - 3, y - 3, 6, 6); }
				
				g.setColor(new Color(0, 0, 255));
				if(World.vehicles.get(m).dir == 1) { g.drawOval(x - 3, y + h[2] - 3, 6, 6); }
				if(World.vehicles.get(m).dir == 2) { g.drawOval(x + h[1] - 3, y - 3, 6, 6); } 
				if(World.vehicles.get(m).dir == 3) { g.drawOval(x - 3, y + h[3] - 3, 6, 6); }
				if(World.vehicles.get(m).dir == 4) { g.drawOval(x + h[0] - 3, y - 3, 6, 6); }
				
				g.setColor(new Color(255, 255, 255));
				g.drawString(World.vehicles.get(m).cSpeed + "", x + 15, y + 25);
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
