package client.entity;

import client.world.World;

public class EntityCarDown extends EntityCar 
{
	public EntityCarDown(String n, int x, int y, int t, int s, int e)
	{
		name = n;
		textureX = t * 24;
		textureY = 48;
		
		posX = x * 24 + 12;
		posY = y * 24 + 12;
		cSpeed = s;
		mSpeed = s;
		
		hitbox[0] = -12;
		hitbox[1] = 12;
		hitbox[2] = 12;
		hitbox[3] = 36;
		extra = e;
		
		dir = 3;
	}
	
	public EntityCarDown(int x, int y, int d, int t, int s, int e)
	{
		this("", x, y, d, s, e);
	}
	
	public void tick()
	{
		collision();
		
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

		hitbox[3] = extra + 24 + ((int) Math.floor(cSpeed));

		posY += (int) cSpeed / 40F;
	}

	public boolean trafficlights()
	{
		for(int t = 0; t < World.trafficlights.size(); t++)
		{
			if( posX > (float) World.trafficlights.get(t).x1 &&
				posX < (float) World.trafficlights.get(t).x2 &&
				posY + hitbox[3] > (float) World.trafficlights.get(t).y1 &&
				posY + hitbox[3] < (float) World.trafficlights.get(t).y2 ) 
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
