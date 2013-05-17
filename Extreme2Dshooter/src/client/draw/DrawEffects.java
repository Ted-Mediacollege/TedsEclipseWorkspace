package client.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import client.world.World;

public class DrawEffects 
{
	private BufferedImage[] textures = new BufferedImage[2];
	
	public DrawEffects()
	{
		try
		{
			textures[0] = ImageIO.read(new File("textures/explosion.png"));
			textures[1] = ImageIO.read(new File("textures/Bullethit.png"));
		}
		catch(Exception e)
		{
			System.out.println("Cannot load textures");
		}
	}
	
	public void paint(Graphics g)
	{
		for(int i = 0; i < World.anims.size(); i++)
		{
			g.drawImage(
				textures[World.anims.get(i).texture], 
				World.anims.get(i).posX - (int) Math.floor(World.anims.get(i).sizeX / 2), 
				World.anims.get(i).posY - (int) Math.floor(World.anims.get(i).sizeY / 2), 
				World.anims.get(i).posX - (int) Math.floor(World.anims.get(i).sizeX / 2) + World.anims.get(i).sizeX, 
				World.anims.get(i).posY - (int) Math.floor(World.anims.get(i).sizeY / 2) + World.anims.get(i).sizeY, 
				0, 
				World.anims.get(i).sizeY * World.anims.get(i).frame, 
				World.anims.get(i).sizeX, 
				World.anims.get(i).sizeY * World.anims.get(i).frame + World.anims.get(i).sizeY, 
				null);
		}
	}
}
