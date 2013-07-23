package aiwar.world;

import java.util.ArrayList;

import aiwar.entity.*;

public class World 
{
	public int[][] terrain = new int[30][30];

	public ArrayList<EntitySoldier> SOLDIERS = new ArrayList<EntitySoldier>();
	
	public World()
	{
		createLevel();
		
		for(int p = 0; p < 40; p++)
		{
			SOLDIERS.add(new EntitySoldierGunner(1, 10.5D, 10.5D));
		}
	}
	
	public void tick()
	{
		for(int i = 0; i < SOLDIERS.size(); i++)
		{
			SOLDIERS.get(i).tick();
		}
	}
	
	public void createLevel()
	{
		terrain = new int[30][20];
		
		for(int i = 0; i < terrain.length; i++)
		{
			for(int j = 0; j < terrain[0].length; j++)
			{
				if(i == 0 || j == 0 || i == terrain.length - 1 || j == terrain[0].length - 1)
				{
					terrain[i][j] = 1;
				}
			}
		}
	}
}
