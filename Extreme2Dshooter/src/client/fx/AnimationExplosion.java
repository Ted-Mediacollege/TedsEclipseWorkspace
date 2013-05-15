package client.fx;

public class AnimationExplosion extends Animation
{
	public AnimationExplosion(int x, int y)
	{
		done = false;
		texture = 0;
		frame = 20;
		posX = x;
		posY = y;
	}
	
	public void tick()
	{
		if(frame > 0)
		{
			frame--;
		}
		else
		{
			done = true;
		}
	}
}
