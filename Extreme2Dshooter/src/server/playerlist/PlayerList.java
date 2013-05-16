package server.playerlist;

import java.util.ArrayList;

public class PlayerList 
{
	public static ArrayList<Player> players = new ArrayList<Player>();
	
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
	
	public static int getNextAvailableID()
	{
		int count = 0;
		
		for(int p = 0; p < 30; p++)
		{
			boolean found = false;
			
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).id == count)
				{
					found = true;
				}
			}
			
			if(found)
			{
				count++;
			}
			else
			{
				//System.out.println("Available id found: " + count);
				return count;
			}
		}
		return -1;
	}
}
