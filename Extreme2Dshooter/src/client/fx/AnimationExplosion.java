package client.fx;

public class AnimationExplosion extends Animation
{
	public int delay = 0;
	
	public AnimationExplosion(int x, int y)
	{
		done = false;
		texture = 0;
		frame = 0;
		posX = x;
		posY = y;
		sizeX = 96;
		sizeY = 96;
	}
	
	public void tick()
	{	
		if(frame < 9)
		{
			delay--;
			if(delay < 0)
			{
				frame++;
				delay = 3;
			}
		}
		else
		{
			done = true;
		}
	}
}
