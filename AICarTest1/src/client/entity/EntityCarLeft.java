package client.entity;

import client.world.World;

public class EntityCarLeft extends EntityCar 
{
	public EntityCarLeft(String n, int x, int y, int t, int s, int e)
	{
		name = n;
		textureX = t * 24;
		textureY = 72;
		
		posX = x * 24 + 12;
		posY = y * 24 + 12;
		cSpeed = s;
		mSpeed = s;
		
		hitbox[0] = -36;
		hitbox[1] = -12;
		hitbox[2] = -12;
		hitbox[3] = 12;
		extra = e;
		
		dir = 4;
	}
	
	public EntityCarLeft(int x, int y, int d, int t, int s, int e)
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
			cSpeed++;
		}
		
		hitbox[0] = -extra + -24 + -((int) Math.floor(cSpeed));

		posX -= (float) cSpeed / 40F;
	}

	public boolean trafficlights()
	{
		for(int t = 0; t < World.trafficlights.size(); t++)
		{
			if( posX + hitbox[0] > (float) World.trafficlights.get(t).x1 &&
				posX + hitbox[0] < (float) World.trafficlights.get(t).x2 &&
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
