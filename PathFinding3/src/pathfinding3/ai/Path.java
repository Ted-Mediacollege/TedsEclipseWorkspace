package pathfinding3.ai;

import java.util.ArrayList;

public class Path 
{
	public int coordX;
	public int coordY;
	public ArrayList<int[]> pathlist;
	
	public Path(int x, int y)
	{
		pathlist = new ArrayList<int[]>();
		pathlist.add(new int[]{x,y});
		coordX = x;
		coordY = y;
	}
	
	public Path(int x, int y, ArrayList<int[]> pl)
	{
		pathlist = pl;
		pathlist.add(new int[]{x,y});
		coordX = x;
		coordY = y;
	}
	
	public void travel(int x, int y)
	{
		pathlist.add(new int[]{coordX,coordY});
		coordX = x;
		coordY = y;
	}
}
