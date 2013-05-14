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
	
	public static void updatePlayer(double px, double py, double vx, double vy)
	{
		if(Network.playerid != -2)
		{
			Network.queue.add("player#" + Network.playerid + "#" + px + "&" + py + "&" + vx + "&" + vy);
		}
	}
}
