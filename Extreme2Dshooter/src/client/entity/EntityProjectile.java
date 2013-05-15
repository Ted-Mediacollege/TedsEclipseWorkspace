package client.entity;

import client.network.Network;
import client.world.World;

public abstract class EntityProjectile extends Entity
{
	public double posR;
	public boolean explodable = false;
	public double exploderange = 0D;
	public int id = -1;
	public boolean hit = false;
	
	public abstract void tick(int s);
	
	public boolean collisionTerrain()
	{
		if(World.level[(int) Math.floor(posX / 24D)][(int) Math.floor(posY / 24D)] > 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean collisionEntity()
	{
		for(int p = 0; p < World.players.size(); p++)
		{
			if(World.players.get(p).id != Network.playerid)
			{
				if( posX > World.players.get(p).hitbox[0][0] &&
					posY > World.players.get(p).hitbox[0][1] &&
					posX < World.players.get(p).hitbox[3][0] &&
					posY < World.players.get(p).hitbox[3][1] )
				{
					System.out.println("PLAYER GOT HIT BY BULLET");
				}
			}
		}
		return false;
	}
	
	public void explode()
	{
		for(int p = 0; p < World.players.size(); p++)
		{
			if( World.players.get(p).posX > posX - exploderange &&
				World.players.get(p).posX < posX + exploderange &&
				World.players.get(p).posY > posY - exploderange &&
				World.players.get(p).posY < posY + exploderange )
			{
				System.out.println("PLAYER GOT HIT BY ROCKET");
			}
		}
	}
}
