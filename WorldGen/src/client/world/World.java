package client.world;

//import java.util.Random;

import client.world.noise.PerlinNoiseGenerator;

public class World 
{
	//private Random rand = new Random();
	private PerlinNoiseGenerator perlinNoise = new PerlinNoiseGenerator(10);
	
	public static float[][] terrain = new float[300][200];
	
	public int gentimer = 0;
	
	public float camX = 0.0F;
	
	public World()
	{
	}
	
	public void tick()
	{
		gentimer--;
		if(gentimer < 0)
		{
			createWorld();
			camX += 0.1F;
			gentimer = 10;
		}
	}
	
	public void createWorld()
	{
		//rand.setSeed(rand.nextLong());
		
		for(int i = 0; i < 300; i++)
		{
			for(int j = 0; j < 200; j++)
			{
				terrain[i][j] = perlinNoise.turbulence2(camX + (float) i / 50F, camX + (float) j / 50F, 20F) ;
			}
		}
	}
}
