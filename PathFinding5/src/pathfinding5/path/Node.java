package pathfinding5.path;

import java.util.ArrayList;

public class Node 
{
	public ArrayList<Integer> connections = new ArrayList<Integer>();
	
	public int nodeID;
	public int posX;
	public int posY;
	
	public boolean visited;

	public Node(int id, int x, int y)
	{
		nodeID = id;
		posX = x;
		posY = y;
		visited = false;
	}
	
	public Node(int id, int x, int y, ArrayList<Integer> c)
	{
		nodeID = id;
		posX = x;
		posY = y;
		connections = c;
		visited = false;
	}

	public Node(int id, int x, int y, int[] c, int l)
	{
		nodeID = id;
		posX = x;
		posY = y;
		
		for(int i = 0; i < l; i++)
		{
			connections.add(c[i]);
		}
		
		visited = false;
	}
	
	public void setConnections(ArrayList<Integer> c)
	{
		connections = c;
	}
}
