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
			gentimer = 5;
			camX += 0.01;
		}
	}
	
	public void createWorld()
	{
		//rand.setSeed(rand.nextLong());
		
		for(int i = 0; i < 300; i++)
		{
			for(int j = 0; j < 200; j++)
			{
				terrain[i][j] = (perlinNoise.turbulence2((camX / 16) + (float) i / 800F, (camX / 16) + (float) j / 800F, 20F) * 400) + (perlinNoise.turbulence2((camX / 2) + (float) i / 100F, (camX / 2) + (float) j / 100F, 20F) * 80) + (perlinNoise.turbulence2(camX + (float) i / 50F, camX + (float) j / 50F, 20F) * 10);
				//terrain[i][j] = (perlinNoise.turbulence2(camX + (float) i / 400F, camX + (float) j / 400F, 20F) * 200);
			}
		}
	}
}
