package client.fx;

public class AnimationBullethit extends Animation
{
	public int delay = 0;
	
	public AnimationBullethit(int x, int y)
	{
		done = false;
		texture = 1;
		frame = 0;
		posX = x;
		posY = y;
		sizeX = 48;
		sizeY = 48;
	}
	
	public void tick()
	{
		if(frame < 6)
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
