package client.world;

public class Chunk 
{
	public int chunkX;
	public int chunkY;
	
	public int[][] terrain;
	
	public Chunk(int x, int y, int[][] t)
	{
		chunkX = x;
		chunkY = y;
		
		terrain = t;
	}
}
