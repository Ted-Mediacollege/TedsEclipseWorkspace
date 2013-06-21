package pathfinding5.path;

import java.util.ArrayList;

public class Path 
{
	public boolean dead = false;
	public int travel = 0;
	public int travelreq = 0;
	public int nodeID = 0;
	public int target = 0;
	private ArrayList<Integer> nodes = new ArrayList<Integer>();
	
	public Path(int i, int t, int q)
	{
		nodeID = i;
		target = t;
		travel = 0;
		travelreq = q;
		nodes.add(i);
	}
	
	public Path(int i, ArrayList<Integer> n, int t, int q)
	{
		nodeID = i;
		target = t;
		nodes = n;
		travel = 0;
		travelreq = q;
	}
	
	public ArrayList<Integer> getNodes()
	{
		return nodes;
	}
}
