package pathfinding5.world;

import java.util.ArrayList;
import pathfinding5.path.Node;
import pathfinding5.path.Path;
import pathfinding5.util.Coords;

public class World 
{
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	public static ArrayList<Path> paths = new ArrayList<Path>();
	public static ArrayList<Integer> calculatedPath = new ArrayList<Integer>();
	
	public int startNode = 0;
	public int endNode = 5;
	
	public boolean calculated = false;
	
	public World()
	{
		create();
		begincalc();
	}
	
	public void tick()
	{
		if(!calculated)
		{
			calc();
		}
	}
	
	public void begincalc()
	{
		nodes.get(startNode).visited = true;
		for(int i = 0; i < nodes.get(startNode).connections.size(); i++)
		{
			paths.add(new Path(nodes.get(startNode).nodeID, nodes.get(startNode).connections.get(i), (int) Math.floor(Coords.getDistance(nodes.get(startNode).posX, nodes.get(startNode).posY, nodes.get(nodes.get(startNode).connections.get(i)).posX, nodes.get(nodes.get(startNode).connections.get(i)).posY))));
		}
	}
	
	public void calc()
	{
		for(int i = 0; i < paths.size(); i++)
		{
			if(!paths.get(i).dead)
			{
				if(nodes.get(paths.get(i).target).visited)
				{
					paths.get(i).dead = true;
				}
				else
				{
					paths.get(i).travel+=5;
					if(paths.get(i).travel > paths.get(i).travelreq)
					{
						paths.get(i).dead = true;
						
						int id = paths.get(i).target;
						nodes.get(id).visited = true;
						
						if(id == endNode)
						{
							calculatedPath = paths.get(i).getNodes();
							calculatedPath.add(id);
							calculated = true;
							break;
						}
						
						for(int j = 0; j < nodes.get(id).connections.size(); j++)
						{
							if(!nodes.get(nodes.get(id).connections.get(j)).visited)
							{
								ArrayList<Integer> newnodes = paths.get(i).getNodes();
								newnodes.add(id);
								
								int newid = nodes.get(nodes.get(id).connections.get(j)).nodeID;
								paths.add(new Path(id, newnodes, newid, (int) Math.floor(Coords.getDistance(nodes.get(id).posX, nodes.get(id).posY, nodes.get(newid).posX, nodes.get(newid).posY))));
								
								newnodes = null;
							}
						}
					}
				}
			}
		}
	}
	
	public void create()
	{
		nodes.add(new Node(0, 100, 100, new int[]{1}, 1));
		nodes.add(new Node(1, 200, 200, new int[]{1,2,3}, 3));
		nodes.add(new Node(2, 400, 200, new int[]{1,4}, 2));
		nodes.add(new Node(3, 200, 500, new int[]{1,4}, 2));
		nodes.add(new Node(4, 400, 400, new int[]{2,3,5}, 3));
		nodes.add(new Node(5, 700, 700, new int[]{4}, 1));
	}
}
