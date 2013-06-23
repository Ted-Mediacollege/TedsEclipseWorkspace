package airtraffic1.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import airtraffic1.logic.AirplaneController;
import airtraffic1.world.World;

public class Draw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private Font font = new Font("arial", Font.PLAIN, 10);
	
	public Draw() 
	{
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0,0,1200,800);

		for(int a = 0; a < World.AIRFIELDS.size(); a++)
		{
			g.setColor(new Color(0,100,0));
			g.drawOval(
				World.AIRFIELDS.get(a).posX - ((int) Math.floor(World.AIRFIELDS.get(a).size / 2)), 
				World.AIRFIELDS.get(a).posY - ((int) Math.floor(World.AIRFIELDS.get(a).size / 2)), 
				World.AIRFIELDS.get(a).size, 
				World.AIRFIELDS.get(a).size
			);
			
			g.setColor(new Color(110,80,0));
			for(int s = 0; s < World.AIRFIELDS.get(a).AIRSTRIPS.size(); s++)
			{
				g.drawPolygon(
					new int[]{
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).stripcoords[0][0]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).stripcoords[1][0]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).stripcoords[2][0]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).stripcoords[3][0])
					}, 
					new int[]{
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).stripcoords[0][1]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).stripcoords[1][1]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).stripcoords[2][1]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).stripcoords[3][1])
					}
				,4
				);
			}
			
			g.setColor(new Color(155,155,155));
			for(int s = 0; s < World.AIRFIELDS.get(a).AIRSTRIPS.size(); s++)
			{
				g.drawPolygon(
					new int[]{
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).arivalcoords[0][0]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).arivalcoords[1][0]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).arivalcoords[2][0]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).arivalcoords[3][0])
					}, 
					new int[]{
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).arivalcoords[0][1]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).arivalcoords[1][1]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).arivalcoords[2][1]),
						(int) Math.floor(World.AIRFIELDS.get(a).AIRSTRIPS.get(s).arivalcoords[3][1])
					}
				,4
				);
			}
		}
		
		g.setFont(font);
		for(int i = 0; i < World.airtraffic.size(); i++)
		{
			if(World.airtraffic.get(i).dead)
			{
				g.setColor(new Color(90,90,90));
			}
			else if(World.airtraffic.get(i).warning)
			{
				g.setColor(new Color(255,0,0));
			}
			else
			{
				g.setColor(new Color(255,255,255));
			}
			
			g.drawRect((int) Math.floor(World.airtraffic.get(i).posX) - 5,(int) Math.floor(World.airtraffic.get(i).posY) - 5, 10, 10);
			g.drawString(World.airtraffic.get(i).name, (int) Math.floor(World.airtraffic.get(i).posX) + 15,(int) Math.floor(World.airtraffic.get(i).posY) + 2);
			g.drawString(((int) Math.floor(World.airtraffic.get(i).feet)) + "F " + ((int) Math.floor(World.airtraffic.get(i).speed)) + "K", (int) Math.floor(World.airtraffic.get(i).posX) + 15,(int) Math.floor(World.airtraffic.get(i).posY) + 13);
			
			for(int j = World.airtraffic.get(i).route.size() - 2; j > -1; j--)
			{
				g.setColor(new Color(55 + (j * 20),55 + (j * 20),55 + (j * 20)));
				g.drawLine(World.airtraffic.get(i).route.get(j)[0], World.airtraffic.get(i).route.get(j)[1], World.airtraffic.get(i).route.get(j + 1)[0], World.airtraffic.get(i).route.get(j + 1)[1]);
			}
		}
		
		for(int n = 0; n < World.AIRFIELDS.size(); n++)
		{
			for(int m = 0; m < World.AIRFIELDS.get(n).AIRSTRIPS.size(); m++)
			{
				AirplaneController plane;
				
				for(int l = 0; l < World.AIRFIELDS.get(n).AIRSTRIPS.get(m).QUEUE.AIRPLANES_QUEUE.size(); l++)
				{
					plane = World.AIRFIELDS.get(n).AIRSTRIPS.get(m).QUEUE.AIRPLANES_QUEUE.get(l);

					if(plane.dead)
					{
						g.setColor(new Color(90,90,90));
					}
					else if(plane.warning)
					{
						g.setColor(new Color(255,0,0));
					}
					else
					{
						g.setColor(new Color(255,255,255));
					}
					
					g.drawRect((int) Math.floor(plane.posX) - 5,(int) Math.floor(plane.posY) - 5, 10, 10);
					g.drawString(plane.name, (int) Math.floor(plane.posX) + 15,(int) Math.floor(plane.posY) + 2);
					g.drawString(((int) Math.floor(plane.feet)) + "F " + ((int) Math.floor(plane.speed)) + "K", (int) Math.floor(plane.posX) + 15,(int) Math.floor(plane.posY) + 13);
					
					for(int j = plane.route.size() - 2; j > -1; j--)
					{
						g.setColor(new Color(55 + (j * 20),55 + (j * 20),55 + (j * 20)));
						g.drawLine(plane.route.get(j)[0], plane.route.get(j)[1], plane.route.get(j + 1)[0], plane.route.get(j + 1)[1]);
					}
				}
				
				if(World.AIRFIELDS.get(n).AIRSTRIPS.get(m).ARIVINGPLANE != null)
				{
					plane = World.AIRFIELDS.get(n).AIRSTRIPS.get(m).ARIVINGPLANE;
	
					if(plane.dead)
					{
						g.setColor(new Color(90,90,90));
					}
					else if(plane.warning)
					{
						g.setColor(new Color(255,0,0));
					}
					else
					{
						g.setColor(new Color(255,255,255));
					}
					
					g.drawRect((int) Math.floor(plane.posX) - 5,(int) Math.floor(plane.posY) - 5, 10, 10);
					g.drawString(plane.name, (int) Math.floor(plane.posX) + 15,(int) Math.floor(plane.posY) + 2);
					g.drawString(((int) Math.floor(plane.feet)) + "F " + ((int) Math.floor(plane.speed)) + "K", (int) Math.floor(plane.posX) + 15,(int) Math.floor(plane.posY) + 13);
					
					for(int j = plane.route.size() - 2; j > -1; j--)
					{
						g.setColor(new Color(55 + (j * 20),55 + (j * 20),55 + (j * 20)));
						g.drawLine(plane.route.get(j)[0], plane.route.get(j)[1], plane.route.get(j + 1)[0], plane.route.get(j + 1)[1]);
					}
				}
			}
		}
		
		g.setColor(new Color(155,155,155));
		for(int t = 0; t < World.points.size(); t++)
		{
			g.setColor(new Color(155,155,155));
			
			int x = World.points.get(t).posX;
			int y = World.points.get(t).posY;
			
			g.drawPolygon(new int[]{0 + x, 5 + x, -5 + x}, new int[]{-5 + y,5 + y,5 + y}, 3);
			g.drawString(World.points.get(t).name, 10+x, 5+y);
		}

        Toolkit.getDefaultToolkit().sync();
        g.dispose();	
	}
	
	public void run()
	{
		repaint();
	}
}
