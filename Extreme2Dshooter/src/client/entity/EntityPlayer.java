package client.entity;

public class EntityPlayer extends EntityLiving
{
	public EntityPlayer(double x, double y)
	{
		name = "Player";
		onfeet = false;
		
		health = 20F;
		
		posX = x;
		posY = y;
		velX = 0;
		velY = 0;
		
		hitbox[0][0] = -12;
		hitbox[0][1] = -12;
		hitbox[1][0] = -12;
		hitbox[1][1] = 12;
		hitbox[2][0] = 12;
		hitbox[2][1] = -12;
		hitbox[3][0] = 12;
		hitbox[3][1] = 12;
	}
	
	public void tick(boolean[] keyinput)
	{
		for(int t = 0; t < 10; t++)
		{
			boolean[] col = collision();
			
			if(col[3] && !onfeet)
			{
				onfeet = true;
			}
			else if(col[3])
			{
				onfeet = true;
			}
			else
			{
				onfeet = false;
			}
			
			if(onfeet)
			{
				if(keyinput[4])
				{
					velY = -5D;
				}
				else
				{
					velY = 0D;
				}
			}
			else
			{
				if(velY < 5)
				{
					velY += 0.02D;
				}
			}
			
			if(keyinput[1] || keyinput[2])
			{
				if(keyinput[1])
				{
					velX = 5D;
				}
				if(keyinput[2])
				{
					velX = -5D;
				}
			}
			else
			{
				if(velX > 0D)
				{
					velX -= 0.1D;
				}
				if(velX < 0D)
				{
					velX += 0.1D;
				}
			}
			
			if(col[0]) { velY = 0D; }
			if(col[1] && velX > 0D) { velX = 0D; }
			if(col[2] && velX < 0D) { velX = 0D; }
			
			posX += (velX / 10D);
			posY += (velY / 10D);
		}
	}
}
