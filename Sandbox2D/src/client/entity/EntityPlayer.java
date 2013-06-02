package client.entity;

import client.world.World;

public class EntityPlayer extends Entity
{	
	public float velX;
	public float velY;
	
	public EntityPlayer(float x, float y)
	{
		posX = x;
		posY = y;
	}
	
	public void tick(boolean[] keyinput)
	{
		velX = 0;
		velY = 0;
		
		if(keyinput[0]) { velY -= 0.1; }
		if(keyinput[1]) { velX -= 0.1; }
		if(keyinput[2]) { velY += 0.1; }
		if(keyinput[3]) { velX += 0.1; }
		
		
		int terrainid = World.getTerrainID(posX + velX, posY + velY);
		if(terrainid != -1)
		{
			posX += velX;
			posY += velY;
		}
		
		//System.out.println(posX + " " + posY);
	}
}
