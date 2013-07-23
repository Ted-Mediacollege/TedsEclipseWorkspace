package com.ted80.tileworld.world.chunk;

public class Chunk 
{
	public int[][][] tiles;
	public int posX;
	public int posY;
	
	public Chunk(int[][][] t, int x, int y)
	{
		tiles = t;
		posX = x;
		posY = y;
	}
}
