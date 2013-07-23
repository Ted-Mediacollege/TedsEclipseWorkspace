package worldgen2.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import worldgen2.WorldGen2;
import worldgen2.world.World;

public class Draw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	public Draw()
	{
		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		System.out.println("draw: start paint");

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 1300, 800);
		
		if(World.terrain != null)
		{
			for(int x = 0; x < World.terrain.length; x++)
			{
				for(int y = 0; y < World.terrain[0].length; y++)
				{
					int c = World.terrain[x][y];
					
					if(c > y - World.snow1[x] || c > (770 - y) - World.snow2[x])
					{
						if(c >= 0 && c < 40)
						{
							g.setColor(new Color(230,230,230));
						}
						else
						{
							g.setColor(new Color(210 - (c - 40),210 - (c - 40),210 - (c - 40)));
						}
					}
					else
					{
						if(c >= 0 && c < 40)
						{
							int mcolor = c * 7;
							int ocolor = 0;
							if(mcolor > 200) { ocolor = mcolor - 200; mcolor = 200; }
							g.setColor(new Color(ocolor,50 + ocolor,50 + mcolor));
						}
						else if(c < 41)
						{
							g.setColor(new Color(194, 178, 128));
						}
						else
						{
							g.setColor(new Color(0,50 + (c * 2),0));
						}
					}
					
					g.drawLine(x, y, x, y);
				}
			}
			g.setColor(new Color(0,0,0));
			g.drawString("Took " + WorldGen2.calctime + " miliseconds to generate. Press N to create a new map", 20, 20);
		}
		else
		{
			g.setColor(new Color(255,255,255));
			g.drawString("Generating...", 600, 400);
		}
		
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
		System.out.println("draw: done paint");
	}

	public void run() 
	{
		System.out.println("draw: run");
		repaint();
	}
}
