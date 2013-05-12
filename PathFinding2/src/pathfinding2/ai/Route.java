package pathfinding2.ai;

import java.util.ArrayList;

public class Route 
{
	public int point;
	public double length;
	public ArrayList<Integer> pointslist;
	
	public Route(int p)
	{
		point = p;
		length = 0;
		pointslist = new ArrayList<Integer>();
		pointslist.add(p);
	}
	
	public void travel(int p, double l)
	{
		point = p;
		pointslist.add(p);
		length += l;
	}
}
