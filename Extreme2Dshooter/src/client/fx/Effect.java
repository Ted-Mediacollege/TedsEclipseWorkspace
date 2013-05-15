package client.fx;

public abstract class Effect 
{
	public boolean done;
	public int posX;
	public int posY;
	
	public abstract void tick();
}
