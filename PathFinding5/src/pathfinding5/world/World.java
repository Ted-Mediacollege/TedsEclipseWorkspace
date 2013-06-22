/*package pathfinding5.world;

import java.util.ArrayList;
import java.util.Random;

import pathfinding5.path.Node;
import pathfinding5.path.Path;
import pathfinding5.util.Coords;

public class World 
{
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	public static ArrayList<Path> paths = new ArrayList<Path>();
	public static int[] calculatedPath = new int[1];
	
	public Random rand = new Random();
	
	public static int startNode = 0;
	public static int endNode = 15;
	
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
					System.out.println("DIE");
					paths.get(i).dead = true;
				}
				else
				{
					paths.get(i).travel+=5;
					if(paths.get(i).travel > paths.get(i).travelreq)
					{
						paths.get(i).dead = true;
						System.out.println("END");
						
						int id = paths.get(i).target;
						nodes.get(id).visited = true;
						
						if(id == endNode)
						{
							paths.get(i).nodes += "," + id;
							String[] pathstring = paths.get(i).nodes.split(",");
							calculatedPath = new int[pathstring.length];
							
							for(int b = 0; b < pathstring.length; b++)
							{
								calculatedPath[b] = Integer.parseInt(pathstring[b]);
							}
							
							calculated = true;
							
							break;
						}
						
						for(int j = 0; j < nodes.get(id).connections.size(); j++)
						{
							if(!nodes.get(nodes.get(id).connections.get(j)).visited)
							{
								String nodeslist = paths.get(i).nodes;
								nodeslist += "," + id;
								
								System.out.println("NEW " + i);
								
								int newid = nodes.get(nodes.get(id).connections.get(j)).nodeID;
								paths.add(new Path(id, nodeslist, newid, (int) Math.floor(Coords.getDistance(nodes.get(id).posX, nodes.get(id).posY, nodes.get(newid).posX, nodes.get(newid).posY))));
							}
						}
					}
				}
			}
		}
	}
}
*/

package pathfinding5.world;

import java.util.ArrayList;
import java.util.Random;

import pathfinding5.path.Node;
import pathfinding5.path.Path;
import pathfinding5.util.Coords;

public class World 
{
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	public static ArrayList<Path> paths = new ArrayList<Path>();
	public static int[] calculatedPath = new int[1];
	
	public Random rand = new Random();

	public static int startNode = 0;
	public static int endNode = 5;

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
							paths.get(i).nodes += "," + id;
							String[] pathstring = paths.get(i).nodes.split(",");
							calculatedPath = new int[pathstring.length];
							
							for(int b = 0; b < pathstring.length; b++)
							{
								calculatedPath[b] = Integer.parseInt(pathstring[b]);
							}
							
							calculated = true;
							
							break;
						}

						for(int j = 0; j < nodes.get(id).connections.size(); j++)
						{
							if(!nodes.get(nodes.get(id).connections.get(j)).visited)
							{
								String nodeslist = paths.get(i).nodes;
								nodeslist += "," + id;

								int newid = nodes.get(nodes.get(id).connections.get(j)).nodeID;
								paths.add(new Path(id, nodeslist, newid, (int) Math.floor(Coords.getDistance(nodes.get(id).posX, nodes.get(id).posY, nodes.get(newid).posX, nodes.get(newid).posY))));
							}
						}
					}
				}
			}
		}
	}

	public void create()
	{
		int count = 0;
		for(int n = 0; n < 150; n++)
		{
			nodes.add(new Node(count, rand.nextInt(900) + 50, rand.nextInt(700) + 50));
		}
		
		for(int i = 0; i < nodes.size(); i++)
		{
			ArrayList<Integer> cons = new ArrayList<Integer>();
			
			for(int j = 0; j < nodes.size(); j++)
			{
				if(i != j && Coords.getDistance(nodes.get(i).posX, nodes.get(i).posY, nodes.get(j).posX, nodes.get(j).posY) < 160)
				{
					cons.add(j);
				}
			}
			
			nodes.get(i).setConnections(cons);
		}
	}
}
