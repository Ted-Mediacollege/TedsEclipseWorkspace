package client.entity;

import client.util.Coords;

public class EntityRocket extends EntityProjectile
{
	public EntityRocket(int i, double x, double y, double r)
	{
		id = i;
		posX = x;
		posY = y;
		posR = r;
		explodable = true;
		exploderange = 50D;
	}
	
	public void tick(int s)
	{
		for(int i = 0; i < s; i++)
		{
			if(!hit)
			{
				posX = Coords.getNextX(posX, posR, 3.0D);
				posY = Coords.getNextY(posY, posR, 3.0D);
				
				boolean entity = collisionEntity();
				boolean terrain = collisionTerrain();
				
				if(entity || terrain)
				{
					explode();
					hit = true;
				}
			}
		}
	}
}
