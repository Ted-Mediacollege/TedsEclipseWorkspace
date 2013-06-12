package pathFinding4.ai;

import java.util.ArrayList;

public class Path 
{
	public boolean dead;
	public int dir;
	public int curX;
	public int curY;
	public ArrayList<int[]> oldpath;

	public Path(int x, int y, int d)
	{
		oldpath = new ArrayList<int[]>();
		curX = x;
		curY = y;
		dir = d;
		dead = false;
	}
	
	public Path(int x, int y, int d, ArrayList<int[]> p)
	{
		oldpath = p;
		curX = x;
		curY = y;
		dir = d;
		dead = false;
	}
}
