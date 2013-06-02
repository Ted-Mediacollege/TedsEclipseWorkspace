package client.ground;

public class Terrain 
{
	public static final Terrain[] terrainList = new Terrain[255];
	
	public static final Terrain grass = (new Terrain(0, 0));
	public static final Terrain dirt = (new Terrain(1, 1));
	public static final Terrain stone = (new Terrain(2, 2));
	public static final Terrain sand = (new Terrain(3, 3));
	
	public final int terrainID;
	public final int texture;
	public float walkSpeed;
	
	public Terrain(int id, int t)
	{
		terrainList[id] = this;
		terrainID = id;
		texture = t;
		walkSpeed = 1.0f;
	}
	
    public Terrain setWalkSpeed(float speed)
    {
        walkSpeed = speed;
        return this;
    }
}
