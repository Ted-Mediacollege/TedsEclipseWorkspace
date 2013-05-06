package laser;

public class ObjectsData 
{
	public static int[][] objectinfo = new int[64][6];
	public static int objectlenght = 0;
	
	public static void createObject(int x, int y, int w, int h)
	{
		objectinfo[objectlenght][0] = x;
		objectinfo[objectlenght][1] = y;
		objectinfo[objectlenght][2] = w;
		objectinfo[objectlenght][3] = h;
		objectinfo[objectlenght][4] = 100;
		objectinfo[objectlenght][5] = 0;
		objectlenght++;
	}
}
