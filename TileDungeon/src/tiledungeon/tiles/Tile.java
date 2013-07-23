package tiledungeon.tiles;

public class Tile 
{
	public static final Tile[] tileList = new Tile[255];

	public static final Tile GreenTop = (new Tile(0, 0, true));
	public static final Tile GreenWall = (new Tile(1, 1, true));
	public static final Tile GreenFloor = (new Tile(2, 20, false));
	
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