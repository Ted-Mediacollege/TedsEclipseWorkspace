package client.field;

public class FieldTrafficLight 
{
	public boolean closed;
	
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	
	public FieldTrafficLight(int i, int j, int k, int l)
	{
		closed = true;
		
		x1 = i;
		y1 = j;
		x2 = k;
		y2 = l;
	}
}
