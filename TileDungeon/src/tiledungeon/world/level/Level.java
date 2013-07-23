package tiledungeon.world.level;

public class Level 
{
	public int[][] tiles;
	public int sizeX;
	public int sizeY;
	
	public Level(int[][] t, int sx, int sy)
	{
		tiles = t;
		sizeX = sx;
		sizeY = sy;
	}
}
