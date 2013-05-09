package client.world;

import java.util.ArrayList;

import client.path.Path;

public class World 
{
	public static ArrayList<Path> paths = new ArrayList<Path>();
	
	public World()
	{
		addTestPaths();
	}
	
	public void tick()
	{
		
	}
	
	public void addTestPaths()
	{
		paths.add(new Path(new int[]{200, 300, 500},new int[]{100, 100, 500}));
	}
}
