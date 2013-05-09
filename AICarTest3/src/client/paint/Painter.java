package client.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Painter extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	public Painter()
	{
		
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 1024, 768);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();	
	}
	
	public void run()
	{
		repaint();
	}
}
