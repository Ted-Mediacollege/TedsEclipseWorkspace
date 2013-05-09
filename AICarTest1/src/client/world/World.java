package client.world;

import java.util.ArrayList;

import client.entity.*;
import client.field.*;

public class World
{
	public WorldLoader loader = new WorldLoader();
	
	public static int[][] Terrain = new int[40][40];
	public static ArrayList<EntityCar> vehicles = new ArrayList<EntityCar>();

	public static ArrayList<FieldTrafficlight> trafficlights = new ArrayList<FieldTrafficlight>();
	public static ArrayList<FieldRemover> removers = new ArrayList<FieldRemover>();
	
	public int crossingstate = 0;
	public int crossingtimer = 0;
	
	public int vehicletimer = 0;
	
	public World()
	{
		loader.load();
		
		trafficlights.add(new FieldTrafficlight(17*24 + 12, 18*24, 18*24, 19*24, 1));
		trafficlights.add(new FieldTrafficlight(22*24, 17*24, 23*24 - 12, 18*24, 2));
		trafficlights.add(new FieldTrafficlight(18*24, 16*24, 20*24, 17*24, 3));
		trafficlights.add(new FieldTrafficlight(20*24, 19*24, 22*24, 20*24, 4));
		
		removers.add(new FieldRemover(39*24, 18*24, 40*24, 19*24));
		removers.add(new FieldRemover(0*24, 17*24, 1*24, 18*24));
		removers.add(new FieldRemover(20*24, 0*24, 22*24, 1*24));
		removers.add(new FieldRemover(18*24, 39*24, 20*24, 40*24));
	}
	
	public void tick()
	{
		if(vehicletimer < 0)
		{
			if(Math.floor(Math.random() * 4) == 0){vehicles.add(new EntityCarRight("CarRight", -2, 18, (int) Math.floor(Math.random() * 10), (int) Math.floor(Math.random() * 10) + 35, (int) Math.floor(Math.random() * 10)));}
			if(Math.floor(Math.random() * 4) == 0){vehicles.add(new EntityCarLeft("CarLeft", 42, 17, (int) Math.floor(Math.random() * 10), (int) Math.floor(Math.random() * 10) + 35, (int) Math.floor(Math.random() * 10)));}
			if(Math.floor(Math.random() * 5) == 0){vehicles.add(new EntityCarDown("CarDown", 19, -2, (int) Math.floor(Math.random() * 10), (int) Math.floor(Math.random() * 10) + 65, (int) Math.floor(Math.random() * 10)));}
			if(Math.floor(Math.random() * 5) == 0){vehicles.add(new EntityCarDown("CarDown", 18, -2, (int) Math.floor(Math.random() * 10), (int) Math.floor(Math.random() * 10) + 55, (int) Math.floor(Math.random() * 10)));}
			if(Math.floor(Math.random() * 5) == 0){vehicles.add(new EntityCarUp("CarUp", 20, 41, (int) Math.floor(Math.random() * 10), (int) Math.floor(Math.random() * 10) + 65, (int) Math.floor(Math.random() * 10)));}
			if(Math.floor(Math.random() * 5) == 0){vehicles.add(new EntityCarUp("CarUp", 21, 41, (int) Math.floor(Math.random() * 10), (int) Math.floor(Math.random() * 10) + 55, (int) Math.floor(Math.random() * 10)));}
			vehicletimer = 30*2;
		}
		vehicletimer--;
		
		if(crossingtimer < 0)
		{
			System.out.println(crossingstate);
			crossingstate++;
			if(crossingstate > 3){crossingstate = 0;}
			if(crossingstate == 0)
			{
				World.trafficlights.get(0).state = false; 
				World.trafficlights.get(1).state = false; 
				crossingtimer = 500; 
			}
			else
			{
				World.trafficlights.get(0).state = true;
				World.trafficlights.get(1).state = true; 
			}
			if(crossingstate == 1)
			{
				crossingtimer = 100;
			}
			if(crossingstate == 2)
			{
				World.trafficlights.get(2).state = false; 
				World.trafficlights.get(3).state = false; 
				crossingtimer = 400; 
			}
			else
			{
				World.trafficlights.get(2).state = true;
				World.trafficlights.get(3).state = true; 
			}
			if(crossingstate == 3)
			{
				crossingtimer = 100;
			}
		}
		crossingtimer--;
		
		for(int i = 0; i < vehicles.size(); i++)
		{
			vehicles.get(i).tick();

			for(int r = 0; r < removers.size(); r++)
			{
				if( vehicles.get(i).posX > removers.get(r).x1 &&
					vehicles.get(i).posX < removers.get(r).x2 &&
					vehicles.get(i).posY > removers.get(r).y1 &&
					vehicles.get(i).posY < removers.get(r).y2 ) 
				{
					vehicles.remove(i);
				}
			}
		}
	}
}
