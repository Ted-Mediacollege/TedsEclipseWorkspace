package client.world;

import java.util.ArrayList;

import client.entity.EntityPlayer;
import client.entity.EntityPlayerOther;

public class World 
{
	public WorldLoader worldloader = new WorldLoader();
	
	public static int[][] level;
	
	public static EntityPlayer player = new EntityPlayer(20D * 24D, 10D * 24D);
	public static ArrayList<EntityPlayerOther> players = new ArrayList<EntityPlayerOther>();
	
	public World()
	{
		level = worldloader.load();
	}
	
	public void tick(boolean[] keyinput)
	{
		player.tick(keyinput);
		
		for(int i = 0; i < players.size(); i++)
		{
			players.get(i).tick();
		}
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
