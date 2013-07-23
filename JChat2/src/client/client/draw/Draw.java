package client.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Draw extends JPanel implements Runnable 
{
	private static final long serialVersionUID = 1L;
	
	private int sizeX;
	private int sizeY;

	public Draw(int sx, int sy)
	{
		sizeX = sx;
		sizeY = sy;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0,0,sizeX,sizeY);
		
        Toolkit.getDefaultToolkit().sync();
        g.dispose();	
	}
	
	@Override
	public void run() 
	{
		repaint();
	}
}
