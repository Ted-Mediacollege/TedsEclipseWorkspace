package airtraffic2.world;

import java.util.ArrayList;

public class ControllerWorld 
{
	public ArrayList<Integer> AIRPLANES_WORLD = new ArrayList<Integer>();
	
	public ControllerWorld()
	{
		
	}
	
	public void tick()
	{
		for(int i = 0; i < AIRPLANES_WORLD.size(); i++)
		{
			for(int j = 0; j < World.AIRPLANES.size(); j++)
			{
				if(AIRPLANES_WORLD.get(i) == World.AIRPLANES.get(j).id)
				{
					if(World.AIRPLANES.get(j).instructions.size() < 1)
					{
						AIRPLANES_WORLD.remove(i);
						World.AIRFIELDS.get(World.AIRPLANES.get(j).airfield).AIRSTRIPS.get(World.AIRPLANES.get(j).airstrip).register(World.AIRPLANES.get(j).id);
						break;
					}
				}
			}
		}
	}
	
	public void register(int id, int arrayid, int[] route, int field, int strip)
	{
		for(int i = 0; i < route.length; i++)
		{
			AIRPLANES_WORLD.add(id);
			World.AIRPLANES.get(arrayid).giveInstruction(World.POINTS.get(route[i]).posX, World.POINTS.get(route[i]).posY, 6000, 250);
			World.AIRPLANES.get(arrayid).airfield = field;
			World.AIRPLANES.get(arrayid).airstrip = strip;
		}
	}
}
