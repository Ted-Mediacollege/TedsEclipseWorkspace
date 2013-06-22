package pathfinding5.path;

public class Path 
{
	public boolean dead = false;
	public int travel = 0;
	public int travelreq = 0;
	public int nodeID = 0;
	public int target = 0;
	public String nodes = "";
	
	public Path(int i, int t, int q)
	{
		nodeID = i;
		target = t;
		travel = 0;
		travelreq = q;
		nodes = i + "";
	}
	
	public Path(int i, String n, int t, int q)
	{
		nodeID = i;
		target = t;
		nodes = n;
		travel = 0;
		travelreq = q;
	}
}
