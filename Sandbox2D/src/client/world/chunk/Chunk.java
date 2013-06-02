package client.world.chunk;

public class Chunk 
{
	public int[][] terrain;
	public int chunkX;
	public int chunkY;
	
	public Chunk(int x, int y, int[][] t)
	{
		terrain = t;
		chunkX = x;
		chunkY = y;
	}
}
