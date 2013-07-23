package aiwar.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import aiwar.Aiwar;

public class Draw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	public Draw()
	{
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0,0,20*30,20*20);

		g.setColor(new Color(80,255,80));
		for(int i = 0; i < Aiwar.world.terrain.length; i++)
		{
			for(int j = 0; j < Aiwar.world.terrain[0].length; j++)
			{
				if(Aiwar.world.terrain[i][j] == 1)
				{
					g.drawRect(i * 20, j * 20, 20, 20);
				}
			}
		}
		
		for(int p = 0; p < Aiwar.world.SOLDIERS.size(); p++)
		{
			int t = Aiwar.world.SOLDIERS.get(p).team;
			if(t == 0) { g.setColor(new Color(255,40,40)); } if(t == 1) { g.setColor(new Color(40,40,255)); }
			g.drawOval((int) Math.floor(Aiwar.world.SOLDIERS.get(p).posX * 20) - 4, (int) Math.floor(Aiwar.world.SOLDIERS.get(p).posY * 20) - 4, 8, 8);
		} 

        Toolkit.getDefaultToolkit().sync();
        g.dispose();	
	}
	
	public void run() 
	{
		repaint();
	}
}
