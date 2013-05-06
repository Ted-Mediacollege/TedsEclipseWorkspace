package client.entity;

import client.world.World;

abstract public class EntityCar 
{	
	public String name;
	public int textureX;
	public int textureY;

	public float posX;
	public float posY;
	public int cSpeed;
	public int mSpeed;
	public int dir;
	
	public int[] hitbox = new int[4];
	public int extra;
	
	abstract public void tick();
	
	public boolean collision()
	{	
		for(int t = 0; t < World.vehicles.size(); t++)
		{
			if( (float) World.vehicles.get(t).posX > posX + hitbox[0] &&
				(float) World.vehicles.get(t).posX < posX + hitbox[1] &&
				(float) World.vehicles.get(t).posY > posY + hitbox[2] &&
				(float) World.vehicles.get(t).posY < posY + hitbox[3] ) 
			{
				return true;
			}
		}
		return false;
	}
}
