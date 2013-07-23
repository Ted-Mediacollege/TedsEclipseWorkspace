package com.ted80.tileworld.tile;

public class Tile 
{
	public static final Tile[] tileList = new Tile[255];

	public static final Tile stone = (new Tile(1, 2, true));
	public static final Tile grass = (new Tile(2, 0, true));
	public static final Tile dirt = (new Tile(3, 1, true));
	
	public final int tileID;
	public final int textureID;
	public final boolean isSolid;
	
	public Tile(int id, int texture, boolean s)
	{
		tileList[id] = this;
		tileID = id;
		textureID = texture;
		isSolid = s;
	}
}