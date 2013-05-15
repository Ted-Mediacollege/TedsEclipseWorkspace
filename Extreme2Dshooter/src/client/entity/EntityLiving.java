package client.entity;

import client.world.World;

public class EntityLiving extends Entity
{
	public String name;
	public boolean onfeet;
	public double velX;
	public double velY;
	public int[][] hitbox = new int[4][2];
	
	public boolean[] collision()
	{
		boolean[] col = new boolean[4];
		
		for(int i = hitbox[0][0]; i < hitbox[2][0]; i++)
		{
			if(World.level[(int) Math.floor((posX + i) / 24D)][(int) Math.floor((posY + hitbox[0][1] - 1) / 24D)] > 0)
			{
				col[0] = true;
			}
		}

		for(int j = hitbox[2][1] + 1; j < hitbox[3][1] - 2; j++)
		{
			if(World.level[(int) Math.floor((posX + hitbox[2][0] + 1) / 24D)][(int) Math.floor((posY + j) / 24D)] > 0)
			{
				col[1] = true;
			}
		}
		for(int k = hitbox[0][1] + 1; k < hitbox[1][1] - 2; k++)
		{
			if(World.level[(int) Math.floor((posX + hitbox[0][0] - 1) / 24D)][(int) Math.floor((posY + k) / 24D)] > 0)
			{
				col[2] = true;
			}
		}
		
		for(int l = hitbox[1][0]; l < hitbox[3][0]; l++)
		{
			if(World.level[(int) Math.floor((posX + l) / 24D)][(int) Math.floor((posY + hitbox[1][1] + 1) / 24D)] > 0)
			{
				col[3] = true;
			}
		}
		
		return col;
	}
}
