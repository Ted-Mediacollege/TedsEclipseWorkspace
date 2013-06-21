package pathFinding4.ai;

public class Path 
{
	public boolean dead;
	public int dir;
	public int curX;
	public int curY;
	public int[][] oldpath;
	public int pathlength;

	public Path(int x, int y, int d)
	{
		oldpath = new int[400][2];
		pathlength = 0;
		curX = x;
		curY = y;
		dir = d;
		dead = false;
	}
	
	public Path(int x, int y, int d, int[][] p, int l)
	{
		pathlength = l;
		oldpath = p;
		curX = x;
		curY = y;
		dir = d;
		dead = false;
	}
}
