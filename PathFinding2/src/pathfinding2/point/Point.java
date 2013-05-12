package pathfinding2.point;

import pathfinding2.world.World;

public class Point 
{
	public String name;
	public double posX;
	public double posY;
	public int[] friends;
	public int id;
	
	public Point(String n, int i, double x, double y, int[] f)
	{
		World.maxPointIDS++;
		
		id = i;
		name = n;
		posX = x;
		posY = y;
		friends = f;
	}
}
