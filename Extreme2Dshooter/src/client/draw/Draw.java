package client.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.Extreme2Dshooter;
import client.network.Network;
import client.world.World;

public class Draw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private BufferedImage terrainTextures;

	public Draw()
	{
		try
		{
			terrainTextures = ImageIO.read(new File("textures/terrain.png"));
		}
		catch(Exception e)
		{
			System.out.println("Cannot load textures");
		}
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		g.setColor(new Color(205, 205, 255));
		g.fillRect(0, 0, 40*24, 30*24);

		g.setColor(new Color(255, 0, 0));
		g.drawRect((int) Math.floor(World.player.posX - 12), (int) Math.floor(World.player.posY - 12), 24, 24);
		
		for(int a = 0; a < World.players.size(); a++)
		{
			if(World.players.get(a).id != Network.playerid)
			{
				g.setColor(new Color(255, 255, 0));
				g.drawRect((int) Math.floor(World.players.get(a).posX - 12), (int) Math.floor(World.players.get(a).posY - 12), 24, 24);
			}
		}
		
		for(int i = 0; i < World.level.length; i++)
		{
			for(int j = 0; j < World.level[0].length; j++)
			{
				int tx = World.level[i][j];
				int ty = 0;
				
				while(tx > 9)
				{
					tx -= 10;
					ty += 1;
				}
				
				g.drawImage(
					terrainTextures, 
					i * 24, 
					j * 24, 
					i * 24 + 24, 
					j * 24 + 24, 
					tx * 24, 
					ty * 24, 
					tx * 24 + 24, 
					ty * 24 + 24, 
					null);
			}
		}
		
		g.setColor(new Color(0, 0, 0));
		g.drawString("FPS: " + Extreme2Dshooter.fps , 10, 15);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}
	
	public void run()
	{
		repaint();
	}
}
