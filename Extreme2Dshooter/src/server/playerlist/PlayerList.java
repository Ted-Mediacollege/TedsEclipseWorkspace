package server.playerlist;

import java.util.ArrayList;

public class PlayerList 
{
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static int nextID;
	
	public PlayerList()
	{
	}
	
	public static int getPlayerFromID(int id)
	{
		for(int p = 0; p < players.size(); p++)
		{
			if(players.get(p).id == id)
			{
				return p;
			}
		}
		
		return -1;
	}
}
