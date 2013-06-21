package pathFinding4.world;

import java.util.ArrayList;
import java.util.Random;

import pathFinding4.ai.Path;

public class World 
{
	private Random rand = new Random();
	
	public static int[][] map = new int[40][40];
	
	private ArrayList<Path> paths;
	public static boolean[][] used;
	public static boolean calculating;
	public static int calctimer = 3;
	
	public static int worldtimer = 3000;
	
	public static boolean calculated;
	public static int[][] path;
	public static int pathlength;
	
	public static int[] start;
	public static int[] target;
	
	public World()
	{
		createNewWorld();
	}
	
	public void setStartAndTarget()
	{
		start = new int[2];
		target = new int[2];
		
		start[0] = 20;
		start[1] = 30;
		target[0] = 10;
		target[1] = 10;
		
		/*boolean f1 = false;
		while(!f1)
		{
			int rx = rand.nextInt(40);
			int ry = rand.nextInt(40);
			
			if(map[rx][ry] == 0)
			{
				f1 = true;
				start[0] = rx;
				start[1] = ry;
				break;
			}
		}
		
		boolean f2 = false;
		while(!f2)
		{
			int rx = rand.nextInt(40);
			int ry = rand.nextInt(40);
			
			if(map[rx][ry] == 0 && rx != start[0] && rx != start[1])
			{
				f1 = true;
				target[0] = rx;
				target[1] = ry;
				break;
			}
		}*/
	}
	
	public void genRandomWorld()
	{
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				//if(rand.nextInt(3) == 0)
				//{
				//	map[i][j] = 1;
				//}
				//else
				//{
					map[i][j] = 0;
				//}
			}
		}
	}
	
	public void tick()
	{
		//worldtimer--;
		//if(worldtimer < 0)
		//{
		//	createNewWorld();
		//	worldtimer = 3000;
		//}
		
		calctimer--;
		if(calctimer < 0)
		{
			if(calculating) { calculate(); }
			
			calctimer = 3;
		}
		
		if(calculated)
		{
			
		}
	}
	
	public void createNewWorld()
	{	
		System.out.println("CREATE WORLD");
		paths = new ArrayList<Path>();
		used = new boolean[40][40];
		calctimer = 0;
		calculated = false;
		calculating = true;
		genRandomWorld();
		setStartAndTarget();
		paths.add(new Path(start[0],start[1],1));
		path = new int[400][2];
		pathlength = 0;
	}
	
	public boolean getEmpty(int x, int y)
	{
		if(x > -1 && x < used[0].length && y > -1 && y < used.length)
		{
			if(!used[x][y] && map[x][y] == 0)
			{
				return true;
			}
		}
		return false;
	}

	public void calculate()
	{
		boolean found = false;
		boolean stuck = true;
		int foundID = 0;
		
		int pathsSize = paths.size();
		for(int p = 0; p < pathsSize; p++) 
		{	
			if(!paths.get(p).dead)
			{
				//LOAD PATH
				int x = paths.get(p).curX;
				int y = paths.get(p).curY;
				int d = paths.get(p).dir;
				int l = paths.get(p).pathlength;
				paths.get(p).oldpath[l][0] = x;
				paths.get(p).oldpath[l][1] = y;
				l += 1;
				
				//CHECK FOR TARGET
				if(x == target[0] && y == target[1])
				{
					found = true;
					foundID = p;
					worldtimer = 40;
					break;
				}
				
				//CREATE NEW PATHS
				if(getEmpty(x, y - 1))// && d != 0)
				{
					int[][] newpath = paths.get(p).oldpath;
					paths.add(new Path(x, y - 1, 0, newpath, l));
					used[x][y - 1] = true;
					stuck = false;
				}
				if(getEmpty(x + 1, y))// && d != 1)
				{
					int[][] newpath = paths.get(p).oldpath;
					paths.add(new Path(x + 1, y, 1, newpath, l));
					used[x + 1][y] = true;
					stuck = false;
				}
				if(getEmpty(x, y + 1))// && d != 2)
				{
					int[][] newpath = paths.get(p).oldpath;
					paths.add(new Path(x, y + 1, 2, newpath, l));
					used[x][y + 1] = true;
					stuck = false;
				}
				if(getEmpty(x - 1, y))// && d != 3)
				{
					int[][] newpath = paths.get(p).oldpath;
					paths.add(new Path(x - 1, y, 3, newpath, l));
					used[x - 1][y] = true;
					stuck = false;
				}
				
				//MOVE PATH
				boolean alive = false;
				/*if(getEmpty(x, y - 1) && d == 0)
				{
					paths.get(p).oldpath.add(new int[]{x,y});
					paths.get(p).curY--;
					used[x][y - 1] = true;
					alive = true;
				}
				if(getEmpty(x + 1, y) && d == 1)
				{
					paths.get(p).oldpath.add(new int[]{x,y});
					paths.get(p).curX++;
					used[x + 1][y] = true;
					alive = true;
				}
				if(getEmpty(x, y + 1) && d == 2)
				{
					paths.get(p).oldpath.add(new int[]{x,y});
					paths.get(p).curY++;
					used[x][y + 1] = true;
					alive = true;
				}
				if(getEmpty(x - 1, y) && d == 3)
				{
					paths.get(p).oldpath.add(new int[]{x,y});
					paths.get(p).curX--;
					used[x - 1][y] = true;
					alive = true;
				}*/
				
				//CHECK IF DEAD
				if(!alive)
				{
					paths.get(p).dead = true;
				}
			}
		}
		
		if(stuck)
		{
			worldtimer = 40;
		}
		
		if(found)
		{
			calculated = true;
			calculating = false;

			System.out.println(foundID + " " + paths.size());
			path = paths.get(foundID).oldpath;
			pathlength = paths.get(foundID).pathlength;
		}
	}
	
	/*
	public void calculate()
	{
		int pathsSize = paths.size();
		for(int p = 0; p < pathsSize; p++) 
		{
			int count = 0;
			int x = paths.get(p).coordX;
			int y = paths.get(p).coordY;
			
			if(x == target[0] && y == target[1])
			{
				calculating = false;
				calculated = true;
				path = paths.get(p).pathlist;
				break;
			}
			if(x + 1 > -1 && y > -1 && x + 1 < 40 && y < 40)
			{
				if(!used[x + 1][y])
				{
					Path path = new Path(paths.get(p).coordX, paths.get(p).coordY, paths.get(p).pathlist);
					path.travel(x + 1, y);
					used[x + 1][y] = true;
					paths.add(path);
					count++;
				}
			}
			if(x - 1 > -1 && y > -1 && x - 1 < 40 && y < 40)
			{
				if(!used[x - 1][y])
				{
					Path path = new Path(paths.get(p).coordX, paths.get(p).coordY, paths.get(p).pathlist);
					path.travel(x - 1, y);
					used[x - 1][y] = true;
					paths.add(path);
					count++;
				}
			}
			if(x > -1 && y + 1 > -1 && x < 40 && y + 1 < 40)
			{
				if(!used[x][y + 1])
				{
					Path path = new Path(paths.get(p).coordX, paths.get(p).coordY, paths.get(p).pathlist);
					path.travel(x, y + 1);
					used[x][y + 1] = true;
					paths.add(path);
					count++;
				}
			}
			if(x > -1 && y - 1 > -1 && x < 40 && y - 1 < 40)
			{
				if(!used[x][y - 1])
				{
					Path path = new Path(paths.get(p).coordX, paths.get(p).coordY, paths.get(p).pathlist);
					path.travel(x, y - 1);
					used[x][y - 1] = true;
					paths.add(path);
					count++;
				}
			}
			paths.remove(p);
		}
	}*/
}
