package worldgen2.world;

import worldgen2.WorldGen2;

public class World implements Runnable
{
	public static int[] snow1;
	public static int[] snow2;
	
	public static int[][] terrain;
	public static PerlinNoise noise;
	
	public void calc()
	{
		System.out.println("world: start calc");
		WorldGen2.calcing = true;
		WorldGen2.calctime = 0;

		terrain = null;
		noise = new PerlinNoise((int) Math.floor(Math.random() * 1000));
		
		int[][] newterrain = new int[1300][800];
		
		for(int x = 0; x < 1300; x++)
		{
			for(int y = 0; y < 800; y++)
			{
				float var = 1.0F + noise.turbulence2(x / 400F, y / 400F, 10) * 2;
				float huge = noise.turbulence2(x / 800F, y / 800F, 10) * (50 * var);
				float big = noise.turbulence2(x / 300F, y / 300F, 10) * (40 * var);
				float lhill = noise.turbulence2(x / 100F, y / 100F, 10) * (20 * var);
				float shill = noise.turbulence2(x / 30F, y / 30F, 10) * 10;
				
				float total = 50 + (huge - 12) + big + lhill + shill;
				if(total > 100F) { total = 100F; }
				if(total < 0F) { total = 0F; }
				
				newterrain[x][y] = (int) Math.floor(total);
			}
		}
		
		int[] newsnow1 = new int[1300];
		int[] newsnow2 = new int[1300];
		for(int s1 = 0; s1 < newsnow1.length; s1++)
		{
			newsnow1[s1] = 30 + (int) Math.floor(noise.turbulence2(s1 / 120F, 0F, 5) * 20);
		}
		for(int s2 = 0; s2 < newsnow2.length; s2++)
		{
			newsnow2[s2] = 30 + (int) Math.floor(noise.turbulence2(s2 / 120F, 1000F, 5) * 20);
		}

		snow1 = newsnow1;
		snow2 = newsnow2;
		terrain = newterrain;
		
		WorldGen2.calcing = false;
		WorldGen2.calctime = Math.round(WorldGen2.calctime);
		System.out.println("world: done calc");

		Thread paint = new Thread(WorldGen2.draw); 
		paint.start(); 
	}
	
	public void run()
	{
		System.out.println("world: run");
		calc();
	}
}
