package client.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Draw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	private DrawPlayer drawplayer = new DrawPlayer();
	private DrawPlayersOther drawothers = new DrawPlayersOther();
	private DrawProjectiles drawprojectiles = new DrawProjectiles();
	private DrawTerrain drawterrain = new DrawTerrain();
	private DrawEffects draweffects = new DrawEffects();
	private DrawDebug drawdebug = new DrawDebug();
	private DrawGui drawgui = new DrawGui();

	public Draw()
	{
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		g.setColor(new Color(205, 205, 255));
		g.fillRect(0, 0, 40*24, 30*24);
		
		drawplayer.paint(g);
		drawothers.paint(g);
		draweffects.paint(g);
		drawprojectiles.paint(g);
		drawterrain.paint(g);
		drawgui.paint(g);
		drawdebug.paint(g);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}
	
	public void run()
	{
		repaint();
	}
}
