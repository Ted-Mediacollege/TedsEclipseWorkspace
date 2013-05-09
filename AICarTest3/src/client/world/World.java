package client.world;

import java.util.ArrayList;

import client.entity.Entity;
import client.path.Path;

public class World 
{
	public static ArrayList<Path> paths = new ArrayList<Path>();
	public static ArrayList<Entity> vehicles = new ArrayList<Entity>();
	
	public World()
	{
		addTestPaths();
	}
	
	public void tick()
	{
		for(int i = 0; i < vehicles.size(); i++)
		{
			vehicles.get(i).tick();
		}
	}
	
	public void addTestPaths()
	{
		paths.add(new Path("TestPath1", 
			new int[]{200, 400, 500, 700},
			new int[]{200, 300, 500, 560})
		);
		paths.add(new Path("TestPath2", 
			new int[]{840, 200, 200, 200, 200},
			new int[]{600, 600, 280, 260, 240})
		);
		//paths.add(new Path("TestPath3", 
		//	new int[]{840, 840, 300, 280, 260},
		//	new int[]{600, 200, 200, 200, 200})
		//);

		vehicles.add(new Entity(-300, 200, 0, 120));
		vehicles.add(new Entity(-200, 200, 0, 120));
		vehicles.add(new Entity(-100, 200, 0, 120));
		vehicles.add(new Entity(0, 200, 0, 120));
		vehicles.add(new Entity(100, 200, 0, 80));
	}
}
