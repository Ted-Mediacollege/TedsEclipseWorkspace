package airtraffic1.world;

import java.util.ArrayList;
import java.util.Random;

import airtraffic1.logic.AirFieldController;
import airtraffic1.logic.AirStripController;
import airtraffic1.logic.AirplaneController;
import airtraffic1.logic.TravelPoint;
import airtraffic1.util.Coords;

public class World 
{
	public static ArrayList<AirFieldController> AIRFIELDS = new ArrayList<AirFieldController>();
	
	public static ArrayList<AirplaneController> airtraffic = new ArrayList<AirplaneController>();
	public static ArrayList<TravelPoint> points = new ArrayList<TravelPoint>();
	public Random rand = new Random();
	
	public static int airplaneSelected = -1;
	
	public World()
	{
		AIRFIELDS.add(new AirFieldController("EHVH", 200, 600, 300));
		AIRFIELDS.get(0).AIRSTRIPS.add(new AirStripController(200, 700, 200, 500, 200, new int[]{0,1}));
		AIRFIELDS.get(0).AIRSTRIPS.add(new AirStripController(120, 670, 300, 580, 200, new int[]{0,1}));
		
		airtraffic.add(new AirplaneController(0, randomAirplaneName(), 100, 100, 90, 1000 + rand.nextInt(4000), 800, 0));

		points.add(new TravelPoint("AGC", 50, 300));
		points.add(new TravelPoint("WGM", 150, 380));
	}
	
	public void tick()
	{
		for(int i = 0; i < airtraffic.size(); i++)
		{
			if(!airtraffic.get(i).dead)
			{
				airtraffic.get(i).warning = false;
				for(int j = 0; j < airtraffic.size(); j++)
				{
					double d = Coords.getDistance(airtraffic.get(i).posX, airtraffic.get(i).posY, airtraffic.get(j).posX, airtraffic.get(j).posY);
					double f = Coords.getDistance(airtraffic.get(i).feet, 0, airtraffic.get(j).feet, 0);
					if(i != j && d < 60 && f < 500)
					{
						airtraffic.get(i).warning = true;
						if(d < 5 && f < 100)
						{
							airtraffic.get(i).dead = true;
							airtraffic.get(j).dead = true;
						}
					}
				}
				
				airtraffic.get(i).tick();
			}
		}
	}
	
	public String randomAirplaneName()
	{
		return ((String) "" + ((char)(rand.nextInt(26) + 'A')) + ((char)(rand.nextInt(26) + 'A')) + ((char)(rand.nextInt(26) + 'A'))) + "" + (rand.nextInt(899) + 100);
	}
}
