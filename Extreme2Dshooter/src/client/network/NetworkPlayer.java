package client.network;

public class NetworkPlayer 
{	
	public NetworkPlayer()
	{
		
	}
	
	public static void registerPlayer()
	{
		Network.queue.add("register");
	}
	
	public static void updatePlayer(double px, double py, double vx, double vy, float h)
	{
		if(Network.playerid != -2)
		{
			Network.queue.add("player#" + Network.playerid + "#" + px + "&" + py + "&" + vx + "&" + vy + "&" + h);
		}
	}
	
	public static void projectile(double x, double y, double r, int type)
	{
		Network.queue.add("projectile#" + Network.playerid + "#" + x + "&" + y + "&" + r + "&" + type);
	}
}
