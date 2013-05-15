package client.world;

import java.util.ArrayList;

import client.anim.Animation;
import client.entity.EntityBullet;
import client.entity.EntityPlayer;
import client.entity.EntityPlayerOther;
import client.entity.EntityProjectile;
import client.network.Network;
import client.util.Coords;

public class World 
{
	public WorldLoader worldloader = new WorldLoader();
	
	public static int[][] level;
	
	public static EntityPlayer player = new EntityPlayer(20D * 24D, 10D * 24D);
	public static ArrayList<EntityPlayerOther> players = new ArrayList<EntityPlayerOther>();
	public static ArrayList<EntityProjectile> projectiles = new ArrayList<EntityProjectile>();
	public static ArrayList<Animation> anims = new ArrayList<Animation>();
	
	public World()
	{
		level = worldloader.load();
	}
	
	public void tick(boolean[] keyinput, int[] mousepos)
	{
		player.tick(keyinput);
		
		for(int i = 0; i < players.size(); i++)
		{
			players.get(i).tick();
		}
		
		for(int p = 0; p < projectiles.size(); p++)
		{
			projectiles.get(p).tick(3);

			if(projectiles.get(p).hit)
			{
				projectiles.remove(p);
			}
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
