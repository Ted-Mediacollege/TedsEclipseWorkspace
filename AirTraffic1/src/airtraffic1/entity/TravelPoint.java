package airtraffic1.entity;

public class TravelPoint 
{
	public String name;
	public int posX;
	public int posY;
	public boolean selected;
	
	public TravelPoint(String n, int x, int y)
	{
		name = n;
		posX = x;
		posY = y;
		selected = false;
	}
}
