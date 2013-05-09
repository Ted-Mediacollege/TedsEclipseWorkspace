package client.path;

public class Path 
{
	public int[][] path;
	
	public Path(int[] pathsX, int[] pathsY)
	{
		path = new int[pathsX.length][2];
		
		for(int i = 0; i < path.length; i++)
		{
			path[i][0] = pathsX[i];
			path[i][1] = pathsY[i];
		}
	}
}
