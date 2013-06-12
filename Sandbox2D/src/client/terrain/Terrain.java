package client.terrain;

import java.util.Random;

public class Terrain 
{
	public static final Terrain stone = (new Terrain(0));
	public static final Terrain grass = (new Terrain(1));
	public static final Terrain dirt = (new Terrain(2));
	public static final Terrain sand = (new Terrain(3));
	public static final Terrain gravel = (new Terrain(4));
	
	public static final Terrain[] terrainList = new Terrain[50];
	
	public final int blockID;
	
	public Terrain(int id)
	{
        terrainList[id] = this;
        blockID = id;
	}
	
	public void updateTick(int x, int y, int z, Random rand) 
	{
		
	}
}
