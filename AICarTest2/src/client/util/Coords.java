package client.util;

public class Coords 
{
	public static double GetDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	public static double GetDegreeFromPoint(int x1, int y1, int x2, int y2)
	{
		return Math.atan2((y2 - y1), (x2 - x1)) * 180 / Math.PI;
	}
}
