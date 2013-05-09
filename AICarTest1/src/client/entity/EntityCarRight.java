package client.entity;

import client.world.World;

public class EntityCarRight extends EntityCar 
{
	public EntityCarRight(String n, int x, int y, int t, int s, int e)
	{
		name = n;
		textureX = t * 24;
		textureY = 24;
		
		posX = x * 24 + 12;
		posY = y * 24 + 12;
		cSpeed = s;
		mSpeed = s;
		
		hitbox[0] = 12;
		hitbox[1] = 36;
		hitbox[2] = -12;
		hitbox[3] = 12;
		extra = e;
		
		dir = 2;
	}
	
	public EntityCarRight(int x, int y, int d, int t, int s, int e)
	{
		this("", x, y, d, s, e);
	}
	
	public void tick()
	{
		if(trafficlights() || collision())
		{
			if(cSpeed > 1)
			{
				cSpeed-=2;
			}
			else if(cSpeed > 0)
			{
				cSpeed-=1;
			}
		}
		else if(cSpeed < mSpeed)
		{
			cSpeed ++;
		}
		
		hitbox[1] = extra + 24 + ((int) Math.floor(cSpeed));
		
		posX += (float) cSpeed / 40F;
	}
	
	public boolean trafficlights()
	{
		for(int t = 0; t < World.trafficlights.size(); t++)
		{
			if( posX + hitbox[1] > (float) World.trafficlights.get(t).x1 &&
				posX + hitbox[1] < (float) World.trafficlights.get(t).x2 &&
				posY > (float) World.trafficlights.get(t).y1 &&
				posY < (float) World.trafficlights.get(t).y2 ) 
			{
				if(World.trafficlights.get(t).state == true)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		return false;
	}
	
}
