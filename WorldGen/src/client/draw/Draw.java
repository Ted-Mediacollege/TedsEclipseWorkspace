package client.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Draw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private DrawTerrain drawterrain = new DrawTerrain();

	public Draw()
	{
		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 1000, 1000);
		
		drawterrain.paint(g);
		
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}
	
	public void run()
	{
		repaint();
	}
}
