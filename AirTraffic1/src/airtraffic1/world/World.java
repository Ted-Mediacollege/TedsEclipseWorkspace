package airtraffic1.world;

import java.util.ArrayList;
import java.util.Random;

import airtraffic1.entity.Airplane;
import airtraffic1.entity.TravelPoint;
import airtraffic1.util.Coords;

public class World 
{
	public static ArrayList<Airplane> airtraffic = new ArrayList<Airplane>();
	public static ArrayList<TravelPoint> points = new ArrayList<TravelPoint>();
	public Random rand = new Random();
	
	public static int airplaneSelected = -1;
	
	public World()
	{
		airtraffic.add(new Airplane(randomAirplaneName(), 100, -10, 90, 1000 + rand.nextInt(4000), 1300));
		airtraffic.add(new Airplane(randomAirplaneName(), 200, -10, 90, 1000 + rand.nextInt(4000), 1300));
		airtraffic.add(new Airplane(randomAirplaneName(), 300, -10, 90, 1000 + rand.nextInt(4000), 1300));
		airtraffic.add(new Airplane(randomAirplaneName(), 400, -10, 90, 1000 + rand.nextInt(4000), 1300));
		airtraffic.add(new Airplane(randomAirplaneName(), 500, -10, 90, 1000 + rand.nextInt(4000), 1300));
		airtraffic.add(new Airplane(randomAirplaneName(), 600, -10, 90, 1000 + rand.nextInt(4000), 1300));
		airtraffic.add(new Airplane(randomAirplaneName(), 700, -10, 90, 1000 + rand.nextInt(4000), 1300));
		airtraffic.add(new Airplane(randomAirplaneName(), 800, -10, 90, 1000 + rand.nextInt(4000), 1300));
		airtraffic.add(new Airplane(randomAirplaneName(), 900, -10, 90, 1000 + rand.nextInt(4000), 1300));
		
		points.add(new TravelPoint("WGM", 200, 400));
		points.add(new TravelPoint("GEF", 500, 400));
		points.add(new TravelPoint("HAV", 700, 600));
	}
	
	public void tick()
	{
		for(int i = 0; i < airtraffic.size(); i++)
		{
			if(!airtraffic.get(i).dead)
			{
				airtraffic.get(i).tick();
	
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
			}
		}
	}
	
	public String randomAirplaneName()
	{
		return ((String) "" + ((char)(rand.nextInt(26) + 'A')) + ((char)(rand.nextInt(26) + 'A')) + ((char)(rand.nextInt(26) + 'A'))) + "" + (rand.nextInt(899) + 100);
	}
	
	public void updateAirplane(int x, int y)
	{
		if(airplaneSelected == -1)
		{
			airplaneSelected = -1;
			for(int i = 0; i < airtraffic.size(); i++)
			{
				if(Coords.getDistance(x, y, airtraffic.get(i).posX, airtraffic.get(i).posY) < 30D)
				{
					airplaneSelected = i;
					break;
				}
			}
		}
		else
		{
			boolean found = false;
			
			for(int j = 0; j < points.size(); j++)
			{
				if(Coords.getDistance(x, y, points.get(j).posX, points.get(j).posY) < 30D)
				{
					airtraffic.get(airplaneSelected).plannedPoints.add(j);
					points.get(j).selected = true;
					found = true;
					break;
				}
			}
			
			if(!found)
			{
				airplaneSelected = -1;
				for(int i = 0; i < airtraffic.size(); i++)
				{
					if(Coords.getDistance(x, y, airtraffic.get(i).posX, airtraffic.get(i).posY) < 30D)
					{
						airplaneSelected = i;
						break;
					}
				}
			}
		}
		
		if(airplaneSelected == -1)
		{
			for(int k = 0; k < points.size(); k++)
			{
				points.get(k).selected = false;
			}
		}
	}
}
