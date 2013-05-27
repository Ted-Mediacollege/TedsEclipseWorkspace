package client.world;

public class WorldGenerator 
{
	public WorldGenerator()
	{
	}
	
	public Chunk generateChunk(int x, int y)
	{
		int[][] terrain = paintTerrain(noiseTerrain(x, y), x, y);
		return new Chunk(x, y, terrain);
	}
	
	public int[][] noiseTerrain(int cx, int cy)
	{
		int[][] noise = new int[20][20];
		
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				noise[i][j] = 0;
			}
		}
		
		return noise;
	}
	
	public int[][] paintTerrain(int[][] cd, int cx, int cy)
	{
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				cd[i][j] = 0;
			}
		}
		
		return cd;
	}
}
