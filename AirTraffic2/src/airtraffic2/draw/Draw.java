package airtraffic2.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import airtraffic2.world.World;

public class Draw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private Font fontSmall = new Font("arial", Font.PLAIN, 10);

	public Draw()
	{
		
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0,0,1200,800);
		
		//TRAVEL POINTS
		g.setFont(fontSmall);
		g.setColor(new Color(155,155,155));
		for(int m = 0; m < World.POINTS.size(); m++)
		{			
			int x = World.POINTS.get(m).posX;
			int y = World.POINTS.get(m).posY;
			g.drawPolygon(new int[]{0 + x, 5 + x, -5 + x}, new int[]{-5 + y,5 + y,5 + y}, 3);
			g.drawString(World.POINTS.get(m).name, 10+x, 5+y);
		}
		
		//AIRFIELDS
		g.setColor(new Color(0,100,0));
		for(int n = 0; n < World.AIRFIELDS.size(); n++)
		{
			g.drawOval(
				World.AIRFIELDS.get(n).posX - ((int) Math.floor(World.AIRFIELDS.get(n).size / 2)), 
				World.AIRFIELDS.get(n).posY - ((int) Math.floor(World.AIRFIELDS.get(n).size / 2)), 
				World.AIRFIELDS.get(n).size, 
				World.AIRFIELDS.get(n).size
			);
			
			for(int o = 0; o < World.AIRFIELDS.get(n).AIRSTRIPS.size(); o++)
			{
				g.setColor(new Color(110,80,0));
				g.drawPolygon(
					new int[]{
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).stripcoords[0][0]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).stripcoords[1][0]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).stripcoords[2][0]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).stripcoords[3][0])
					}, 
					new int[]{
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).stripcoords[0][1]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).stripcoords[1][1]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).stripcoords[2][1]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).stripcoords[3][1])
					}
				,4
				);

				g.setColor(new Color(155,155,155));
				g.drawPolygon(
					new int[]{
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).arivalcoords[0][0]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).arivalcoords[1][0]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).arivalcoords[2][0]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).arivalcoords[3][0])
					}, 
					new int[]{
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).arivalcoords[0][1]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).arivalcoords[1][1]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).arivalcoords[2][1]),
						(int) Math.floor(World.AIRFIELDS.get(o).AIRSTRIPS.get(o).arivalcoords[3][1])
					}
				,4
				);
			}
		}
		
		//AIRCRAFT
		g.setFont(fontSmall);
		for(int i = 0; i < World.AIRPLANES.size(); i++)
		{
			for(int j = 1; j < World.AIRPLANES.get(i).history.size(); j++)
			{
				g.setColor(new Color(70 + (j * 12),70 + (j * 12),70 + (j * 12)));
				g.drawLine(World.AIRPLANES.get(i).history.get(j - 1)[0], World.AIRPLANES.get(i).history.get(j - 1)[1], World.AIRPLANES.get(i).history.get(j)[0], World.AIRPLANES.get(i).history.get(j)[1]);
			}
			
			if(World.AIRPLANES.get(i).dead) { g.setColor(new Color(90,90,90)); }
			else if(World.AIRPLANES.get(i).warning) { g.setColor(new Color(255,0,0)); }
			else { g.setColor(new Color(255,255,0)); }
			
			int h = (int) Math.floor(World.AIRPLANES.get(i).posR) + 90;
			if(h < 0) { h += 360; }
			g.drawRect((int) Math.floor(World.AIRPLANES.get(i).posX) - 5,(int) Math.floor(World.AIRPLANES.get(i).posY) - 5, 10, 10);
			g.drawString(World.AIRPLANES.get(i).name + " " + h + "H", (int) Math.floor(World.AIRPLANES.get(i).posX) + 13,(int) Math.floor(World.AIRPLANES.get(i).posY) - 2);
			g.drawString(((int) Math.floor(World.AIRPLANES.get(i).posZ)) + "F " + ((int) Math.floor(World.AIRPLANES.get(i).speed)) + "K", (int) Math.floor(World.AIRPLANES.get(i).posX) + 13,(int) Math.floor(World.AIRPLANES.get(i).posY) + 9);
		}

        Toolkit.getDefaultToolkit().sync();
        g.dispose();	
	}
	
	public void run()
	{
		repaint();
	}
}
