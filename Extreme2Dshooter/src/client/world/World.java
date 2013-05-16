package client.world;

import java.util.ArrayList;

import client.entity.EntityBullet;
import client.entity.EntityPlayer;
import client.entity.EntityPlayerOther;
import client.entity.EntityProjectile;
import client.fx.Animation;
import client.network.Network;
import client.util.Coords;

public class World 
{
	public WorldLoader worldloader = new WorldLoader();
	
	public static int[][] level;
	
	public static double spawnX = 20D * 24D;
	public static double spawnY = 10D * 24D;
	
	public static EntityPlayer player = new EntityPlayer(spawnX, spawnY);
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
		
		for(int a = 0; a < anims.size(); a++)
		{
			anims.get(a).tick();
			
			if(anims.get(a).done)
			{
				anims.remove(a);
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
