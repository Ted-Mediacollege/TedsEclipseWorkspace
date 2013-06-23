package airtraffic1.logic;

import java.util.ArrayList;

import airtraffic1.util.Coords;
import airtraffic1.world.World;

public class AirFieldController 
{
	public ArrayList<AirplaneController> AIRPLANES_ARIVAL = new ArrayList<AirplaneController>();
	public ArrayList<AirStripController> AIRSTRIPS = new ArrayList<AirStripController>();
	
	public String name;
	public int posX;
	public int posY;
	public int size;
	
	public AirFieldController(String n, int x, int y, int s)
	{
		name = n;
		posX = x;
		posY = y;
		size = s;
	}
	
	public void tick()
	{
		for(int i = 0; i < AIRPLANES_ARIVAL.size(); i++)
		{
			AIRPLANES_ARIVAL.get(i).tick();
		}
		
		for(int s = 0; s < AIRSTRIPS.size(); s++)
		{
			if(AIRSTRIPS.get(s).ARIVINGPLANE == null)
			{
				AIRSTRIPS.get(s).ARIVINGPLANE.tick();
			}
			
			for(int t = 0; t < AIRSTRIPS.get(s).QUEUE.AIRPLANES_QUEUE.size(); t++)
			{
				AIRSTRIPS.get(s).QUEUE.AIRPLANES_QUEUE.get(t).tick();
			}
		}
	}
	
	public void requestLanding(AirplaneController airplane)
	{
		/*boolean found = false;
		for(int i = 0; i < AIRSTRIPS.size(); i++)
		{
			if(AIRSTRIPS.get(i).ARIVINGPLANE == null)
			{
				AIRSTRIPS.get(i).ARIVINGPLANE = airplane;
				World.airtraffic.remove(airplane.id);
				found = true;
				break;
			}
		}
		
		if(!found)
		{*/
			int closepoint = 1;
			int closedistance = 1000;
			for(int q = 0; q < AIRSTRIPS.size(); q++)
			{
				int d = (int) Math.floor(Coords.getDistance(airplane.posX, airplane.posY, World.points.get(AIRSTRIPS.get(q).QUEUE.queuepoints[0]).posX, World.points.get(AIRSTRIPS.get(q).QUEUE.queuepoints[0]).posY));
				if(d < closedistance)
				{
					closedistance = d;
					closepoint = q;
				}
			}
			AIRSTRIPS.get(closepoint).QUEUE.AIRPLANES_QUEUE.add(airplane);
			World.airtraffic.remove(airplane.id);
			
		//}
	}
}
