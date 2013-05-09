package client.world;

public class World 
{
	public static int[][] path = new int[8][2];

	public static double[] ball = new double[5];
	
	public World()
	{
		create();
	}
	
	public void tick()
	{
		load();
		
		if(GetDistance((int) ball[0], (int) ball[1], path[(int) ball[3]][0], path[(int)ball[3]][1]) < 20.0D )
		{
			ball[3]++;
			
			if(ball[3] >= path.length)
			{
				ball[3] = 0;
			}
		}

		if(GetDegreeFromPoint((int) ball[0], (int) ball[1], path[(int) ball[3]][0], path[(int) ball[3]][1]) < ball[2] - 180)
		{
			ball[2] -= 360;
		}
		if(GetDegreeFromPoint((int) ball[0], (int) ball[1], path[(int) ball[3]][0], path[(int) ball[3]][1]) > ball[2] + 180)
		{
			ball[2] += 360;
		}
		
		if(GetDegreeFromPoint((int) ball[0], (int) ball[1], path[(int) ball[3]][0], path[(int) ball[3]][1]) < ball[2])
		{
			ball[2] -= 3.0D;
		}
		if(GetDegreeFromPoint((int) ball[0], (int) ball[1], path[(int) ball[3]][0], path[(int) ball[3]][1]) > ball[2])
		{
			ball[2] += 3.0D;
		}
		
		ball[0] += ball[4] * Math.cos(ball[2] * Math.PI / 180.0);
		ball[1] += ball[4] * Math.sin(ball[2] * Math.PI / 180.0);
	}
	
	public double GetDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	public double GetDegreeFromPoint(int x1, int y1, int x2, int y2)
	{
		return Math.atan2((y2 - y1), (x2 - x1)) * 180 / Math.PI;
	}
	
	public void create()
	{
		ball[0] = 100;
		ball[1] = 100;
		ball[2] = 45;
		ball[3] = 0;
		ball[4] = 2.0D;
	}
	
	public void load()
	{
		path[0][0] = 100; path[0][1] = 200;
		path[1][0] = 500; path[1][1] = 200;
		path[2][0] = 800; path[2][1] = 200;
		path[3][0] = 700; path[3][1] = 400;
		path[4][0] = 800; path[4][1] = 600;
		path[5][0] = 500; path[5][1] = 500;
		path[6][0] = 200; path[6][1] = 600;
		path[7][0] = 300; path[7][1] = 400;
	}
}
