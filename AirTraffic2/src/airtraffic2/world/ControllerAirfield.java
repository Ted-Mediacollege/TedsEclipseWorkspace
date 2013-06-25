package airtraffic2.world;

import java.util.ArrayList;

public class ControllerAirfield 
{
	public ArrayList<ControllerAirstrip> AIRSTRIPS = new ArrayList<ControllerAirstrip>();
	
	public int posX;
	public int posY;
	public int size;
	
	public ControllerAirfield(int x, int y, int s)
	{
		posX = x;
		posY = y;
		size = s;
	}
}
