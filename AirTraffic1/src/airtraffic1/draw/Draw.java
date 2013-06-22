package airtraffic1.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

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
		g.fillRect(0,0,1024,768);

		g.setFont(font);
		for(int i = 0; i < World.airtraffic.size(); i++)
		{
			if(i == World.airplaneSelected)
			{
				g.setColor(new Color(255,255,0));
			}
			else if(World.airtraffic.get(i).dead)
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
			g.drawString(((int) Math.floor(World.airtraffic.get(i).feet)) + "F " + ((int) Math.floor(World.airtraffic.get(i).speed)) + "N", (int) Math.floor(World.airtraffic.get(i).posX) + 15,(int) Math.floor(World.airtraffic.get(i).posY) + 13);
			
			for(int j = World.airtraffic.get(i).route.size() - 2; j > -1; j--)
			{
				g.setColor(new Color(55 + (j * 20),55 + (j * 20),55 + (j * 20)));
				g.drawLine(World.airtraffic.get(i).route.get(j)[0], World.airtraffic.get(i).route.get(j)[1], World.airtraffic.get(i).route.get(j + 1)[0], World.airtraffic.get(i).route.get(j + 1)[1]);
			}
		}
		
		g.setColor(new Color(155,155,155));
		for(int t = 0; t < World.points.size(); t++)
		{
			if(World.points.get(t).selected)
			{
				g.setColor(new Color(0,255,0));
			}
			else
			{
				g.setColor(new Color(155,155,155));
			}
			
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
