package client.field;

public class FieldTrafficlight extends Field
{
	public boolean state = true;
	
	public int[][] green;
	public int[][] red;
	
	public FieldTrafficlight(int posx1, int posy1, int posx2, int posy2, int id)
	{
		x1 = posx1;
		x2 = posx2;
		y1 = posy1;
		y2 = posy2;
		
		createTextures(id);
	}
	
	public void createTextures(int id)
	{
		if(id == 1)
		{
			green = new int[2][4];
			red = new int[2][4];
			
			green[0][0] = 24*17 + 6; green[0][1] = 24*18; green[0][2] = 24*3; green[0][3] = 24*4;
			green[1][0] = 24*17 + 6; green[1][1] = 24*19; green[1][2] = 24*3; green[1][3] = 24*5;
			red[0][0] = 24*17 + 6; red[0][1] = 24*18; red[0][2] = 24*2; red[0][3] = 24*4;
			red[1][0] = 24*17 + 6; red[1][1] = 24*19; red[1][2] = 24*2; red[1][3] = 24*5;
		}
		
		if(id == 2)
		{
			green = new int[2][4];
			red = new int[2][4];
			
			green[0][0] = 24*22 - 6; green[0][1] = 24*16; green[0][2] = 24*0; green[0][3] = 24*4;
			green[1][0] = 24*22 - 6; green[1][1] = 24*17; green[1][2] = 24*0; green[1][3] = 24*5;
			red[0][0] = 24*22 - 6; red[0][1] = 24*16; red[0][2] = 24*1; red[0][3] = 24*4;
			red[1][0] = 24*22 - 6; red[1][1] = 24*17; red[1][2] = 24*1; red[1][3] = 24*5;
		}
		
		if(id == 3)
		{
			green = new int[3][4];
			red = new int[3][4];
			
			green[0][0] = 24*17; green[0][1] = 24*16; green[0][2] = 24*3; green[0][3] = 24*2;
			green[1][0] = 24*18; green[1][1] = 24*16; green[1][2] = 24*4; green[1][3] = 24*2;
			green[2][0] = 24*19; green[2][1] = 24*16; green[2][2] = 24*5; green[2][3] = 24*2;
			red[0][0] = 24*17; red[0][1] = 24*16; red[0][2] = 24*3; red[0][3] = 24*3;
			red[1][0] = 24*18; red[1][1] = 24*16; red[1][2] = 24*4; red[1][3] = 24*3;
			red[2][0] = 24*19; red[2][1] = 24*16; red[2][2] = 24*5; red[2][3] = 24*3;
		}
		
		if(id == 4)
		{
			green = new int[3][4];
			red = new int[3][4];
			
			green[0][0] = 24*20; green[0][1] = 24*19; green[0][2] = 24*0; green[0][3] = 24*3;
			green[1][0] = 24*21; green[1][1] = 24*19; green[1][2] = 24*1; green[1][3] = 24*3;
			green[2][0] = 24*22; green[2][1] = 24*19; green[2][2] = 24*2; green[2][3] = 24*3;
			red[0][0] = 24*20; red[0][1] = 24*19; red[0][2] = 24*0; red[0][3] = 24*2;
			red[1][0] = 24*21; red[1][1] = 24*19; red[1][2] = 24*1; red[1][3] = 24*2;
			red[2][0] = 24*22; red[2][1] = 24*19; red[2][2] = 24*2; red[2][3] = 24*2;
		}
	}
}
