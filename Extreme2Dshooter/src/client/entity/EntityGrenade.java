package client.entity;

import client.fx.AnimationExplosion;
import client.util.Coords;
import client.world.World;

public class EntityGrenade extends EntityProjectile
{
	public double velY;
	
	public EntityGrenade(int i, double x, double y, double r)
	{
		id = i;
		posX = x;
		posY = y;
		posR = r;
		velY = -3.0D;
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
					World.anims.add(new AnimationExplosion((int) Math.floor(posX), (int) Math.floor(posY)));
					explode();
					hit = true;
				}
			}
		}
	}
}
