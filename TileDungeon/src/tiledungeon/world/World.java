package tiledungeon.world;

import tiledungeon.world.level.Level;
import tiledungeon.world.level.LevelLoader;

public class World 
{
	public static Level currentlevel;
	public static Camera camera;
	
	public World()
	{
		currentlevel = LevelLoader.load("test");
		camera = new Camera(100, 600);
	}
	
	public void tick()
	{
	}
}
