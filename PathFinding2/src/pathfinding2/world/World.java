package pathfinding2.world;

import java.util.ArrayList;

import pathfinding2.ai.Route;
import pathfinding2.point.Point;
import pathfinding2.util.Coords;

public class World 
{
	public static Point[] points;
	public static int maxPointIDS = 0;
	public int size = 2;
	
	public int start;
	public int target;
	
	public World()
	{
		createWorld();
		calculateRoute(0, 3);
	}
	
	public void tick()
	{
		
	}
	
	public void calculateRoute(int s, int t)
	{
		ArrayList<Route> routes = new ArrayList<Route>();
		boolean found = false;
		boolean[] map = new boolean[maxPointIDS];
		
		start = s;
		target = t;
		routes.add(new Route(start));
		
		int breaksafety = 0;
		while(!found)
		{
			int loopsize = routes.size();
			System.out.println(loopsize);
			for(int i = 0; i < loopsize; i++)
			{
				if(points[routes.get(i).point].friends.length > 1)
				{
					for(int f = 0; f < points[routes.get(i).point].friends.length; f++)
					{
						if(map[points[routes.get(i).point].friends[f]] == false)
						{
							Route copy = routes.get(i);
							
							//System.out.println(i + " " + f + "");
							
							if(i > 200)
							{
								break;
							}
							
							double distance = Coords.getDistance(
								points[routes.get(i).point].posX, 
								points[routes.get(i).point].posY, 
								points[points[routes.get(i).point].friends[f]].posX, 
								points[points[routes.get(i).point].friends[f]].posY);

							copy.travel(points[routes.get(i).point].friends[f], distance);
							routes.add(copy);
							
							if(points[routes.get(i).point].friends[f] == target)
							{
								found = true;
							}
							
							map[points[routes.get(i).point].friends[f]] = true;
						}
					}
					
					routes.remove(i);
				}
				if(i > 200)
				{
					break;
				}
			}
			
			breaksafety++;
			if(breaksafety > 100) { break; }
		}
	}
	
	public void createWorld()
	{
		points = new Point[100];
		
		int count = 0;
		for(int a = 0; a < size; a++)
		{
			for(int b = 0; b < size; b++)
			{
				int friendsize = 0;
				int[] friends = new int[4];
				if(a > 0){ friends[friendsize] = count - size; friendsize++; }
				if(b > 0){ friends[friendsize] = count - 1; friendsize++; }
				if(a < size - 1){ friends[friendsize] = count + size; friendsize++; }
				if(b < size - 1){ friends[friendsize] = count + 1; friendsize++; }
				
				System.out.println(friendsize);
				
				int[] f = new int[friendsize];
				for(int c = 0; c < friendsize; c++)
				{
					f[c] = friends[c];
				}
				
				points[count] = new Point("point-" + count, count, (80D * b) + 200, (80D * a) + 100, f);
				count++;
			}
		}
	}
}


/*
		ArrayList<Route> routes = new ArrayList<Route>();
		boolean[] map = new boolean[maxPointIDS];
		boolean found = false;
		
		System.out.println(map.length);
		
		for(int f = 0; f < map.length; f++)
		{
			map[f] = true;
		}
		
		routes.add(new Route(start));
		map[start] = false;
		
		int safetybreak = 0;
		while(!found)
		{
			for(int i = 0; i < routes.size(); i++)
			{
				if(points[routes.get(i).point].friends.length > 1)
				{
					for(int j = 0; j < points[routes.get(i).point].friends.length; j++)
					{
						if(map[points[points[routes.get(i).point].friends[j]].id] == true)
						{
							Route routecopy = routes.get(i);
							double oldX = points[routes.get(i).point].posX;
							double oldY = points[routes.get(i).point].posY;
							double nextX = points[points[routes.get(i).point].friends[j]].posX;
							double nextY = points[points[routes.get(i).point].friends[j]].posY;
							
							routecopy.length += Coords.getDistance(oldX, oldY, nextX, nextY);
							routecopy.travel(points[points[routes.get(i).point].friends[j]].id);
							System.out.println(points[points[routes.get(i).point].friends[j]].id);
							routes.add(routecopy);
							
							if(points[points[routes.get(i).point].friends[j]].id == target)
							{
								found = true;
							}
							
							map[points[points[routes.get(i).point].friends[j]].id] = false;
						}
					}
					
					routes.remove(i);
				}
			}
			
			safetybreak++;
			if(safetybreak > 30)
			{
				break;
			}
		}
		
		if(found) { System.out.println("ROUTE FOUND"); } else { System.out.println("SAFETYBREAK BROKE THE LOOP"); }
*/