package client.painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.world.World;

public class Painter extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private BufferedImage textureCars;

	public Painter()
	{
		try
		{
			textureCars = ImageIO.read(new File("textures/cars.png"));
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

		for(int i = 0; i < World.path.length; i++)
		{
			g.setColor(new Color(255, 255, 0));
			if(i == 0)
			{
				g.drawLine(World.path[World.path.length - 1][0], World.path[World.path.length - 1][1], World.path[i][0], World.path[i][1]);
			}
			else
			{
				g.drawLine(World.path[i - 1][0], World.path[i - 1][1], World.path[i][0], World.path[i][1]);
			}
			g.drawOval(World.path[i][0] - 40, World.path[i][1] - 40, 80, 80);
			g.drawOval(World.path[i][0] - 5, World.path[i][1] - 5, 10, 10);
			
			g.setColor(new Color(255, 255, 255));
			g.drawString(GetDegreeFromPoint((int) World.ball[0], (int) World.ball[1], World.path[i][0], World.path[i][1]) + "", World.path[i][0] + 30, World.path[i][1] + 30);
		}
		
		g.setColor(new Color(255, 0, 0));
		g.drawOval((int) Math.floor(World.ball[0] - 5), (int) Math.floor(World.ball[1] - 5), 10, 10);
		g.drawLine((int) Math.floor(World.ball[0]), (int) Math.floor(World.ball[1]), World.path[(int) World.ball[3]][0], World.path[(int) World.ball[3]][1]);
		
		g.setColor(new Color(0, 255, 0));
		g.drawOval(
			(int) Math.floor(World.ball[0] + GetDistance((int) World.ball[0], (int) World.ball[1], World.path[(int) World.ball[3]][0], World.path[(int) World.ball[3]][1]) * Math.cos(World.ball[2] * Math.PI / 180.0) - 5), 
			(int) Math.floor(World.ball[1] + GetDistance((int) World.ball[0], (int) World.ball[1], World.path[(int) World.ball[3]][0], World.path[(int) World.ball[3]][1]) * Math.sin(World.ball[2] * Math.PI / 180.0) - 5), 
			10, 
			10
			);
		
		g.drawLine(
			(int) Math.floor(World.ball[0]), 
			(int) Math.floor(World.ball[1]), 
			(int) Math.floor(World.ball[0] + GetDistance((int) World.ball[0], (int) World.ball[1], World.path[(int) World.ball[3]][0], World.path[(int) World.ball[3]][1]) * Math.cos(World.ball[2] * Math.PI / 180.0) - 5), 
			(int) Math.floor(World.ball[1] + GetDistance((int) World.ball[0], (int) World.ball[1], World.path[(int) World.ball[3]][0], World.path[(int) World.ball[3]][1]) * Math.sin(World.ball[2] * Math.PI / 180.0) - 5)
		);		

		g.setColor(new Color(255, 255, 255));
		g.drawString(World.ball[2] + "", (int) Math.floor(World.ball[0]) + 10, (int) Math.floor(World.ball[1]));

        Toolkit.getDefaultToolkit().sync();
        g.dispose();	
	}

	public double GetDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	public double GetDegreeFromPoint(int x1, int y1, int x2, int y2)
	{
		return Math.atan2((y2 - y1), (x2 - x1)) * 180 / Math.PI;
	}
	
	public void run()
	{
		repaint();
	}
}	
