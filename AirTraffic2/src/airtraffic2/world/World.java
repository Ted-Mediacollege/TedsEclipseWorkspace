package airtraffic2.world;

import java.util.ArrayList;
import java.util.Random;

public class World 
{
	public static ArrayList<Airplane> AIRPLANES = new ArrayList<Airplane>();
	public static ArrayList<TravelPoint> POINTS = new ArrayList<TravelPoint>();
	public static ControllerWorld WORLDCONTROL = new ControllerWorld();
	public static ArrayList<ControllerAirfield> AIRFIELDS = new ArrayList<ControllerAirfield>();
	
	public Random rand = new Random();
	
	public int idcounter = 0;
	public int spawntimer = 0;
	
	public World()
	{	
		AIRFIELDS.add(new ControllerAirfield(600, 600, 300));
		AIRFIELDS.get(0).AIRSTRIPS.add(new ControllerAirstrip(700, 600, 500, 600, 300, new int[]{3,4}));
		
		POINTS.add(new TravelPoint("WGM", 100, 100));
		POINTS.add(new TravelPoint("IGY", 180, 240));
		POINTS.add(new TravelPoint("AUV", 190, 350));
		POINTS.add(new TravelPoint("TR1", 120, 520));
		POINTS.add(new TravelPoint("TR2", 60, 440));
		POINTS.add(new TravelPoint("GSW", 65, 290));
		POINTS.add(new TravelPoint("FSW", 95, 660));
	}
	
	public void tick()
	{
		for(int i = 0; i < AIRPLANES.size(); i++)
		{
			AIRPLANES.get(i).update();
		}
		
		WORLDCONTROL.tick();
		for(int j = 0; j < AIRFIELDS.size(); j++)
		{
			for(int k = 0; k < AIRFIELDS.get(j).AIRSTRIPS.size(); k++)
			{
				AIRFIELDS.get(j).AIRSTRIPS.get(k).tick();
			}
		}
		
		spawntimer--;
		if(spawntimer < 0)
		{
			int r1 = rand.nextInt(3);
			if(r1 == 0) { AIRPLANES.add(new Airplane(idcounter, randomAirplaneName(), rand.nextInt(100), -50, 3000 + rand.nextInt(5000), 90, 700)); WORLDCONTROL.register(idcounter, AIRPLANES.size() - 1, new int[]{0,1,2,3}, 0, 0); }
			if(r1 == 1) { AIRPLANES.add(new Airplane(idcounter, randomAirplaneName(), -50, 250 + rand.nextInt(100), 3000 + rand.nextInt(5000), 90, 700)); WORLDCONTROL.register(idcounter, AIRPLANES.size() - 1, new int[]{5,3}, 0, 0); }
			if(r1 == 2) { AIRPLANES.add(new Airplane(idcounter, randomAirplaneName(), rand.nextInt(100), 800, 3000 + rand.nextInt(5000), 90, 700)); WORLDCONTROL.register(idcounter, AIRPLANES.size() - 1, new int[]{6,4}, 0, 0); }
			idcounter++;
			spawntimer = 120;
		}
	}
	
	public String randomAirplaneName()
	{
		return ((String) "" + ((char)(rand.nextInt(26) + 'A')) + ((char)(rand.nextInt(26) + 'A')) + ((char)(rand.nextInt(26) + 'A'))) + "" + (rand.nextInt(899) + 100);
	}
}
