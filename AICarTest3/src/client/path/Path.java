package client.path;

public class Path 
{
	public int[][] path;
	public String pathName;
	
	public Path(String n, int[] pathsX, int[] pathsY)
	{
		pathName = n;
		path = new int[pathsX.length][2];
		
		for(int j = 0; j < path.length; j++)
		{
			path[j][0] = pathsX[j];
			path[j][1] = pathsY[j];
		}
	}
}
