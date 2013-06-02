package client.world;

import java.util.ArrayList;

import client.entity.EntityPlayer;
import client.ground.Terrain;
import client.world.chunk.Chunk;

public class World 
{
	public static ArrayList<Chunk> chunklist = new ArrayList<Chunk>();
	public static EntityPlayer player;
	
	public static float camX = 0; 
	public static float camY = 100; 
	
	public World()
	{
		createTestChunk();
		player = new EntityPlayer(0.5f,0.5f);
	}
	
	public void createTestChunk()
	{
		int[][] t = new int[10][10];
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(i > 5) { t[i][j] = Terrain.dirt.terrainID; }
				else { t[i][j] = Terrain.grass.terrainID; }
			}
		}
		
		chunklist.add(new Chunk(0, 0, t));
		chunklist.add(new Chunk(1, 0, t));
	}
	
	public void tick(boolean[] keyinput)
	{
		player.tick(keyinput);
		
		camX = (-player.posX * 48) + 512;
		camY = (player.posY * 48) + 384;
		System.out.println(camX);
	}
	
	public static int getTerrainID(float x, float y)
	{
		int chunkX = (int) Math.floor(x / 10);
		int chunkY = (int) Math.floor(y / 10);
		int chunkID = getChunkIdFromCoords(chunkX,chunkY);
		
		if(chunkID != -1)
		{
			return chunklist.get(chunkID).terrain[(int) Math.floor(x - (chunkX * 10))][(int) Math.floor(y - (chunkY * 10))];
		}
		else
		{
			return -1;
		}
	}
	
	public static int getChunkIdFromCoords(int x, int y)
	{
		for(int c = 0; c < chunklist.size(); c++)
		{
			if(chunklist.get(c).chunkX == x && chunklist.get(c).chunkY == y)
			{
				return c;
			}
		}
		
		return -1;
	}
}
