package laser;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Painter extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public float color = 0F;
	public float[] colors = new float[4];

	public Painter()
	{
		colors[0] = 0.0F;
		colors[1] = 0.2F;
		colors[2] = 0.4F;
		colors[3] = 0.6F;
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 1900, 980);		

		color += 0.002F;
		if(color > 0.998F)
		{
			color = 0F;
		}

		for(int l = 0; l < 4; l++)
		{
			g.setColor(new Color(Color.HSBtoRGB(colors[l], 1.0F, 1.0F)));
			for(int j = 0; j < LaserData.laserlenght[l]; j++)
			{
				if(j == LaserData.laserlenght[l] - 1)
				{
					g.setColor(new Color(Color.HSBtoRGB(0F, 0.0F, 1.0F)));
				}
				
				g.drawLine(
					(int) Math.floor(LaserData.Laserinfo[l][j][0]), 
					(int) Math.floor(LaserData.Laserinfo[l][j][1]), 
					(int) Math.floor(LaserData.Laserinfo[l][j][2]), 
					(int) Math.floor(LaserData.Laserinfo[l][j][3])
				);	
			}
		}

		for(int i = 0; i < ObjectsData.objectlenght; i++)
		{
			if(ObjectsData.objectinfo[i][4] < 100)
			{
				ObjectsData.objectinfo[i][4]++;
			}

			if(ObjectsData.objectinfo[i][5] > 0)
			{
				ObjectsData.objectinfo[i][5]--;
			}
			
			float c = ((float) ObjectsData.objectinfo[i][4] / 100F);
			float l = ((float) ObjectsData.objectinfo[i][5] / 100F);
			
			g.setColor(new Color(Color.HSBtoRGB(color, c, l + 0.5F)));
			g.fillRect(
				ObjectsData.objectinfo[i][0], 
				ObjectsData.objectinfo[i][1], 
				ObjectsData.objectinfo[i][2], 
				ObjectsData.objectinfo[i][3]
			);	
			
			g.setColor(new Color(0, 0, 0));
			g.fillRect(
					ObjectsData.objectinfo[i][0] + 10, 
					ObjectsData.objectinfo[i][1] + 10, 
					ObjectsData.objectinfo[i][2] - 20, 
					ObjectsData.objectinfo[i][3] - 20
				);	
		}
		
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}
}
