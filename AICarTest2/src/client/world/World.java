package client.world;

public class World 
{
	//posX, posY, speed, angle
	public static double[] vehicle = new double[4];
	
	public World()
	{
		vehicle[0] = 100;
		vehicle[1] = 100;
		vehicle[2] = 1.0D;
		vehicle[3] = 0;
	}
	
	public void tick(boolean[] input)
	{
		if(input[0]) { vehicle[2] += 0.1D; } //speed ++
		if(input[1]) { vehicle[3] += vehicle[2]; } //angle right
		if(input[2]) { vehicle[2] -= 0.1D; } //speed --
		if(input[3]) { vehicle[3] -= vehicle[2]; } //angle left
		
		vehicle[0] += vehicle[2] * Math.cos(vehicle[3] * Math.PI / 180.0);
		vehicle[1] += vehicle[2] * Math.sin(vehicle[3] * Math.PI / 180.0);
	}
}
