package com.ted80.tileworld.world;

import java.util.ArrayList;
import java.util.Random;

import com.ted80.tileworld.tile.Tile;
import com.ted80.tileworld.world.chunk.Chunk;
import com.ted80.tileworld.world.noise.PerlinNoiseGenerator;

public class World 
{
	public Random rand = new Random();
	public PerlinNoiseGenerator noise = new PerlinNoiseGenerator();
	
	public static float camX = 0 * 20;
	public static float camY = 45 * 20;
	
	public static ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	
	public World()
	{
		chunks.add(createTestChunk(0,0));
		chunks.add(createTestChunk(1,0));
		chunks.add(createTestChunk(2,0));
		chunks.add(createTestChunk(3,0));
		chunks.add(createTestChunk(4,0));
		chunks.add(createTestChunk(5,0));
		
		chunks.add(createTestChunk(0,1));
		chunks.add(createTestChunk(1,1));
		chunks.add(createTestChunk(2,1));
		chunks.add(createTestChunk(3,1));
		chunks.add(createTestChunk(4,1));
		chunks.add(createTestChunk(5,1));
		
		chunks.add(createTestChunk(0,2));
		chunks.add(createTestChunk(1,2));
		chunks.add(createTestChunk(2,2));
		chunks.add(createTestChunk(3,2));
		chunks.add(createTestChunk(4,2));
		chunks.add(createTestChunk(5,2));
		
		chunks.add(createTestChunk(0,3));
		chunks.add(createTestChunk(1,3));
		chunks.add(createTestChunk(2,3));
		chunks.add(createTestChunk(3,3));
		chunks.add(createTestChunk(4,3));
		chunks.add(createTestChunk(5,3));
		
		chunks.add(createTestChunk(0,4));
		chunks.add(createTestChunk(1,4));
		chunks.add(createTestChunk(2,4));
		chunks.add(createTestChunk(3,4));
		chunks.add(createTestChunk(4,4));
		chunks.add(createTestChunk(5,4));
	}
	
	public void tick()
	{
		
	}
	
	public static int getHighestTile(int x, int y)
	{
		int cx = (int) Math.floor(x / 10);
		int cy = (int) Math.floor(y / 10);
		
		for(int c = 0; c < chunks.size(); c++)
		{
			if(cx == chunks.get(c).posX && cy == chunks.get(c).posY)
			{
				int ex = 0; if(cx < 1) { ex = 1; }
				int ey = 0; if(cy < 1) { ey = 1; }
				
				for(int z = 9; z > -1; z--)
				{
					if(chunks.get(c).tiles[x - (cx * 10)][y - (cy * 10)][z] > 0)
					{
						return z;
					}
				}
			}
		}
		
		return -1;
	}
	
	public Chunk createTestChunk(int px, int py)
	{
		int[][][] tiles = new int[10][10][40];
		
		for(int x = 0; x < 10; x++)
		{
			for(int y = 0; y < 10; y++)
			{
				int h = (int) Math.floor(noise.turbulence2((float) (x + px * 10) / 50F, (float) (y + py * 10) / 50F, 10F) * 50) + 20;
				
				if(h < 2) { h = 1; } else if(h > 39) { h = 39; }
				
				for(int z = 0; z < 40; z++)
				{
					if(z == h)
					{
						tiles[x][y][z] = Tile.grass.tileID;
					}
					else if(z < h && z > h - 2)
					{
						tiles[x][y][z] = Tile.dirt.tileID;
					}
					else if(z < h)
					{
						tiles[x][y][z] = Tile.stone.tileID;
					}
					else
					{
						tiles[x][y][z] = 0;
					}
				}
			}
		}
		
		Chunk chunk = new Chunk(tiles, px, py);
		return chunk;
	}
}
