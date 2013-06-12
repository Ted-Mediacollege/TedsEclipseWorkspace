package pathFinding4.ai;

import java.util.ArrayList;

public class Path 
{
	public boolean dead;
	public int dir;
	public int curX;
	public int curY;
	public ArrayList<int[]> path;

	public Path(int x, int y, int d)
	{
		path = new ArrayList<int[]>();
		curX = x;
		curY = y;
		dir = d;
		dead = false;
	}
	
	public Path(int x, int y, int d, ArrayList<int[]> p)
	{
		path = p;
		curX = x;
		curY = y;
		dir = d;
		dead = false;
	}
}
